package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

import java.util.List;

public class FuncionarioBuscarPorNomeUseCAse {

    private final FuncionarioGateway gateway;

    public FuncionarioBuscarPorNomeUseCAse(FuncionarioGateway gateway) {
        this.gateway = gateway;
    }

    public List<Funcionario> execute(String nome){
        return gateway.findByNomeContainsIgnoreCase(nome);
    }
}
