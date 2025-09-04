package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CRUDBackend.exception.itensEstoque.ItemEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public class ItemEstoqueBuscarPorIdUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueBuscarPorIdUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public ItemEstoque execute(Integer id){
        return gateway.findById(id)
                .orElseThrow(() -> new ItemEstoqueNaoEncontradoException("O item procurado não existe"));
    }

}
