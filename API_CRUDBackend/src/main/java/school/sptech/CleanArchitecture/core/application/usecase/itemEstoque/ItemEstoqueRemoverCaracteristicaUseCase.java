package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public class ItemEstoqueRemoverCaracteristicaUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueRemoverCaracteristicaUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public ItemEstoque execute(ItemEstoque itemEstoque, Categoria caracteristica){
        itemEstoque.getCaracteristicas().remove(caracteristica);
        return gateway.save(itemEstoque);
    }

}
