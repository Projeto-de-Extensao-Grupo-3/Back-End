package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.DefeitosPorRoupaDto;

import java.time.LocalDateTime;
import java.util.List;

public class DefeitosPorRoupaUseCase {

    private final ItemEstoqueGateway gateway;

    public DefeitosPorRoupaUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<DefeitosPorRoupaDto> execute(String dataInicio, String dataFim, String caracteristica, String categoria) {
        return gateway.defeitosPorRoupa(dataInicio, dataFim, caracteristica, categoria);
    }
}
