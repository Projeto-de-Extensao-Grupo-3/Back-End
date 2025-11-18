package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.funcionario.FuncionarioNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

public class BuscarFuncionarioPorIdUseCase {

    private final FuncionarioGateway gateway;

    public BuscarFuncionarioPorIdUseCase(FuncionarioGateway gateway) {
        this.gateway = gateway;
    }

    public Funcionario execute(Integer id){
        return gateway.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionario nao encotnrado"));
    }
}
