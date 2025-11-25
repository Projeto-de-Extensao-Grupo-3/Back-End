package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.DefeitosPorRoupaDto;

import java.util.List;

public class DefeitosPorRoupaUseCase {

    private final ItemEstoqueGateway gateway;

    public DefeitosPorRoupaUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<DefeitosPorRoupaDto> execute() {
        return gateway.defeitosPorRoupa();
    }
}
