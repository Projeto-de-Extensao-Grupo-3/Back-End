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
        List<LoteEmEstoqueDto> lotesEmEstoque =  gateway.findLotesEmEstoque();
        return lotesEmEstoque.stream().map(
                lote -> new LoteEmEstoqueDto(lote.getIdLote(), lote.getNomeItem(), lote.getQtdItem(), lote.getIdItem(),
                        lote.getPrecoItem() == null ? 0.0 : lote.getPrecoItem())
        ).collect(Collectors.toList());
    }
}
