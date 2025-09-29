package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

import java.util.Optional;

public class BuscarFuncionarioPorIdUseCase {

    private final FuncionarioGateway gateway;

    public BuscarFuncionarioPorIdUseCase(FuncionarioGateway gateway) {
        this.gateway = gateway;
    }

    public Optional<Funcionario> execute(Integer id){
        return gateway.findById(id);
    }
}
