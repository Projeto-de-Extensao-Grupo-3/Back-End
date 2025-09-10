package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.application.exception.funcionario.FuncionarioNaoEncontradoException;

public class FuncionarioRemoverPorIdUseCase {

    private final FuncionarioGateway gateway;

    public FuncionarioRemoverPorIdUseCase(FuncionarioGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Integer id){
        if (gateway.existsById(id)) {
            gateway.deleteById(id);
        } else {
            throw new FuncionarioNaoEncontradoException("O funcionário para atualizar não existe.");
        }
    }
}
