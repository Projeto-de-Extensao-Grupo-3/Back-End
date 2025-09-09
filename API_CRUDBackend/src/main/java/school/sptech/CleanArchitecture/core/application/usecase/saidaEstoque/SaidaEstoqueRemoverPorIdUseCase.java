package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.exception.SaidaEstoque.SaidaEstoqueNaoEncontradoException;

public class SaidaEstoqueRemoverPorIdUseCase {

    private final SaidaEstoqueGateway gateway;

    public SaidaEstoqueRemoverPorIdUseCase(SaidaEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Integer id){
        if (gateway.existsById(id)){
            gateway.deleteById(id);
        }
        else {
            throw new SaidaEstoqueNaoEncontradoException("A saída não existe para remover");
        }
    }
}
