package school.sptech.CleanArchitecture.core.application.usecase.lote;

import school.sptech.CleanArchitecture.core.adapters.LoteGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;

import java.util.List;

public class ListarTodosLotesUseCase {

    private final LoteGateway gateway;

    public ListarTodosLotesUseCase(LoteGateway gateway) {
        this.gateway = gateway;
    }

    public List<Lote> execute() {
        return gateway.findAll();
    }
}
