package school.sptech.CleanArchitecture.core.application.usecase.categoria;

import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.categoria.CategoriaBadRequestException;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueListarItensCaracteristicaUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueRemoverCaracteristicaUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public class CaracteristicaRemoverPorIdUseCase {

    private final CategoriaGateway gateway;

    private final ItemEstoqueListarItensCaracteristicaUseCase listarItensCaracteristicaUseCase;

    private final ItemEstoqueRemoverCaracteristicaUseCase removerCaracteristicaUseCase;

    public CaracteristicaRemoverPorIdUseCase(CategoriaGateway gateway, ItemEstoqueListarItensCaracteristicaUseCase listarItensCaracteristicaUseCase, ItemEstoqueRemoverCaracteristicaUseCase removerCaracteristicaUseCase) {
        this.gateway = gateway;
        this.listarItensCaracteristicaUseCase = listarItensCaracteristicaUseCase;
        this.removerCaracteristicaUseCase = removerCaracteristicaUseCase;
    }

    public void execute(Integer id){
        if (gateway.existsById(id)) {
            Categoria categoria = gateway.findById(id);
            Integer idPai = categoria.getCategoriaPai().getIdCategoria();
            if (idPai == 3){
                List<ItemEstoque> itensComCaracteristica = listarItensCaracteristicaUseCase.execute(categoria.getIdCategoria());
                itensComCaracteristica.stream().map(i -> removerCaracteristicaUseCase.execute(i, categoria));
                gateway.deleteById(id);
            }
            throw new CategoriaBadRequestException("O objeto não corresponde a uma caracteristica pois não possui ID da categoria pai como 3.");
        } else {
            throw new CategoriaNaoEncontradaException("Categoria com ID " + id + " não encontrada");
        }
    }

}
