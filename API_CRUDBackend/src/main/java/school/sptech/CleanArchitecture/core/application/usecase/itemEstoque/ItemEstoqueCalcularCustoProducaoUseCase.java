package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.itensEstoque.ItemEstoqueNaoEncontradoException;

public class ItemEstoqueCalcularCustoProducaoUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueCalcularCustoProducaoUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public Double execute(Integer id){
        if (gateway.existsById(id)) {
            return gateway.calcularCustoProducao(id);
        }
        throw new ItemEstoqueNaoEncontradoException("O item para calcular custo não existe");
    }
}
