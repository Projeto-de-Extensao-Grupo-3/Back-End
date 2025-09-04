package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.exception.itensEstoque.ItemEstoqueNaoEncontradoException;

public class ItemEstoqueRemoverPorIdUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueRemoverPorIdUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Integer id){
        if (gateway.existsById(id)) {
            gateway.deleteById(id);
        }
            throw new ItemEstoqueNaoEncontradoException("O item para atualizar não existe");
    }
}
