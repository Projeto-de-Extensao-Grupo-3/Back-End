package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.PaginacaoResponseDTO;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.SaidaPaginacaoDTO;

@Service
@RequiredArgsConstructor
public class BuscarLotesPaginadoSaidaUseCase {

    private final LoteItemEstoqueGateway gateway;

    public PaginacaoResponseDTO<SaidaPaginacaoDTO> executar(int page, int limit) {
        int offset = page * limit;
        return gateway.buscarPaginadoSaida(offset, limit);
    }
}
