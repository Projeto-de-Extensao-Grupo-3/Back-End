package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;

import java.util.List;

public class BuscarTodosLoteItemEstoqueUseCase {

    private final LoteItemEstoqueGateway gateway;

    public BuscarTodosLoteItemEstoqueUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<LoteItemEstoque> execute() {
        return gateway.findAll();
    }
}
