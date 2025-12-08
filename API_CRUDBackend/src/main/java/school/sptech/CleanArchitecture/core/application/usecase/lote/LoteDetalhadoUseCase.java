package school.sptech.CleanArchitecture.core.application.usecase.lote;

import school.sptech.CleanArchitecture.core.adapters.LoteGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.lote.LoteDetalhadoDto;

import java.util.List;

public class LoteDetalhadoUseCase {

    private final LoteGateway gateway;

    public LoteDetalhadoUseCase(LoteGateway gateway) {
        this.gateway = gateway;
    }

    public List<LoteDetalhadoDto> execute(){
        return gateway.findLoteDetalhado();
    }
}
