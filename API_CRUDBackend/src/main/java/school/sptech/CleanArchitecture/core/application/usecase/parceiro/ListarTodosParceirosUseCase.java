package school.sptech.CleanArchitecture.core.application.usecase.parceiro;

import school.sptech.CleanArchitecture.core.adapters.ParceiroGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

import java.util.List;

public class ListarTodosParceirosUseCase {

    private final ParceiroGateway gateway;

    public ListarTodosParceirosUseCase(ParceiroGateway gateway) {
        this.gateway = gateway;
    }

    public List<Parceiro> execute() {
        return gateway.findAll();
    }
}
