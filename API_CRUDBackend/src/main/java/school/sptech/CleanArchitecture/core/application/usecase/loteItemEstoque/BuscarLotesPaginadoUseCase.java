package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.EntradaPaginacaoDTO;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.PaginacaoResponseDTO;

@Service
@RequiredArgsConstructor
public class BuscarLotesPaginadoUseCase {

    private final LoteItemEstoqueGateway gateway;

    public PaginacaoResponseDTO<EntradaPaginacaoDTO> executar(int page, int limit) {
        int offset = page * limit;
        return gateway.buscarPaginado(offset, limit);
    }
}