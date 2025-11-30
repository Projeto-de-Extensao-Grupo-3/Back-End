package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque.TaxaDefeitoCosturaDto;

import java.time.LocalDateTime;
import java.util.List;

public class TaxaDefeitoCosturaUseCase {
    private SaidaEstoqueGateway gateway;

    public TaxaDefeitoCosturaUseCase(SaidaEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<TaxaDefeitoCosturaDto> executar(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return gateway.calcularTaxaDefeitoCostura(dataInicio, dataFim);
    }
}
