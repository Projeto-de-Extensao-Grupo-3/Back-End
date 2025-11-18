package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;

public class BuscarPorIdLoteItemEstoqueUseCase {

    private final LoteItemEstoqueGateway gateway;

    public BuscarPorIdLoteItemEstoqueUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public LoteItemEstoque executar(Integer id) {
        return gateway.findById(id);
    }
}
