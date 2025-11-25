package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.PecasMaiorMaoObraDto;

import java.util.List;

public class PecasMaiorMaoObraDtoUseCase {

    private final LoteItemEstoqueGateway gateway;

    public PecasMaiorMaoObraDtoUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<PecasMaiorMaoObraDto> execute() {
        return gateway.buscarPecasMaiorMaoObra();
    }
}
