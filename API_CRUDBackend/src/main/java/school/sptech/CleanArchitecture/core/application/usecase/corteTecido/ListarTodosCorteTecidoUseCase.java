package school.sptech.CleanArchitecture.core.application.usecase.corteTecido;

import school.sptech.CleanArchitecture.core.adapters.CorteTecidoGateway;
import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;

import java.util.List;

public class ListarTodosCorteTecidoUseCase {

    private final CorteTecidoGateway gateway;

    public ListarTodosCorteTecidoUseCase(CorteTecidoGateway gateway) {
        this.gateway = gateway;
    }

    public List<CorteTecido> execute() {
        return gateway.findAll();
    }
}
