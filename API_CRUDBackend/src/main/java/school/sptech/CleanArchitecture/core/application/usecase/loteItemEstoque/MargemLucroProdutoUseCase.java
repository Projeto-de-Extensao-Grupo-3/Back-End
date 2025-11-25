package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.MargemLucroProdutoDto;

import java.util.List;

public class MargemLucroProdutoUseCase {

    private final LoteItemEstoqueGateway gateway;

    public MargemLucroProdutoUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<MargemLucroProdutoDto> execute() {
        return gateway.buscarMargemLucroProdutos();
    }
}
