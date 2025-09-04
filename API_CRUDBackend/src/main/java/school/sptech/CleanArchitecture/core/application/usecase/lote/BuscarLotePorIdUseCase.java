package school.sptech.CleanArchitecture.core.application.usecase.lote;

import school.sptech.CleanArchitecture.core.adapters.LoteGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;

public class BuscarLotePorIdUseCase {

    private final LoteGateway gateway;

    public BuscarLotePorIdUseCase(LoteGateway gateway) {
        this.gateway = gateway;
    }

    public Lote executar(Integer id) {
        return gateway.findById(id);
    }
}
