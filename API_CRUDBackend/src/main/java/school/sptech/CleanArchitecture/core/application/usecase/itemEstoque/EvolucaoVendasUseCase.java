package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.EvolucaoVendasDto;

import java.time.YearMonth;
import java.util.List;

public class EvolucaoVendasUseCase {

    private final ItemEstoqueGateway gateway;

    public EvolucaoVendasUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<EvolucaoVendasDto> execute(YearMonth dataInicio, YearMonth dataFim, String caracteristica, String categoria) {
        return gateway.evolucaoVendas(dataInicio, dataFim, caracteristica, categoria);
    }
}
