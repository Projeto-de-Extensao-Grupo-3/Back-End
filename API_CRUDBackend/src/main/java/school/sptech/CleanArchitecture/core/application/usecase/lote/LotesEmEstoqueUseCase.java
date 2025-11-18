package school.sptech.CleanArchitecture.core.application.usecase.lote;

import school.sptech.CleanArchitecture.core.adapters.LoteGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.lote.LoteEmEstoqueDto;

import java.util.List;
import java.util.stream.Collectors;

public class LotesEmEstoqueUseCase {

    private final LoteGateway gateway;

    public LotesEmEstoqueUseCase(LoteGateway gateway) {
        this.gateway = gateway;
    }

    public List<LoteEmEstoqueDto> execute(){
        return   gateway.findLotesEmEstoque();
    }

}
