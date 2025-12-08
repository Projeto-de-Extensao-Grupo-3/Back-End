package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.funcionario.FuncionarioNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

import java.util.List;

public class FuncionarioBuscarPorEmailUseCase {

    private final FuncionarioGateway gateway;

    public FuncionarioBuscarPorEmailUseCase(FuncionarioGateway gateway) {
        this.gateway = gateway;
    }

    public Funcionario execute(String email){
        return gateway.findByEmail(email)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionario nao encontrado"));
    }
}
