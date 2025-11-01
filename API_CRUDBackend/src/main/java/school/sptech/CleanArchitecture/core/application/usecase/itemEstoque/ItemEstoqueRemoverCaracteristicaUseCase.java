package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import jakarta.transaction.Transactional;
import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.usecase.categoria.CategoriaBuscarPorIdUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public class ItemEstoqueRemoverCaracteristicaUseCase {

    private final ItemEstoqueGateway gateway;

    private final CategoriaBuscarPorIdUseCase categoriaBuscarPorIdUseCase;

    private final ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarPorIdUseCase;

    public ItemEstoqueRemoverCaracteristicaUseCase(ItemEstoqueGateway gateway, CategoriaBuscarPorIdUseCase categoriaBuscarPorIdUseCase, ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarPorIdUseCase) {
        this.gateway = gateway;
        this.categoriaBuscarPorIdUseCase = categoriaBuscarPorIdUseCase;
        this.itemEstoqueBuscarPorIdUseCase = itemEstoqueBuscarPorIdUseCase;
    }

    @Transactional
    public ItemEstoque execute(ItemEstoque itemEstoque, Categoria caracteristica){
        System.out.println(itemEstoque);
        gateway.removerCaracteristica(caracteristica.getIdCategoria());

        ItemEstoque itemComCaracteristicaRemovida = itemEstoqueBuscarPorIdUseCase.execute(itemEstoque.getIdItemEstoque());
        System.out.println(itemComCaracteristicaRemovida);
        return itemComCaracteristicaRemovida;
    }

}
