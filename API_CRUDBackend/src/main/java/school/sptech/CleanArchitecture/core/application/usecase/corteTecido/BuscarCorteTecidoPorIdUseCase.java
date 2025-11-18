package school.sptech.CleanArchitecture.core.application.usecase.corteTecido;

import school.sptech.CleanArchitecture.core.adapters.CorteTecidoGateway;
import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;

public class BuscarCorteTecidoPorIdUseCase {

    private final CorteTecidoGateway gateway;

    public BuscarCorteTecidoPorIdUseCase(CorteTecidoGateway gateway) {
        this.gateway = gateway;
    }

    public CorteTecido executar(Integer id) {
        return gateway.findById(id);
    }
}
