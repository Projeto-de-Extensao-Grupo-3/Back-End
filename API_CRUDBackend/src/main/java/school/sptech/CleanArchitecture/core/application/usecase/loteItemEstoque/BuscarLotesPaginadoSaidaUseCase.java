package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.config.redis.SaidaPaginacaoService;
import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.EntradaPaginacaoDTO;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.PaginacaoResponseDTO;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.SaidaPaginacaoDTO;

@Service
@RequiredArgsConstructor
public class BuscarLotesPaginadoSaidaUseCase {

    private final LoteItemEstoqueGateway gateway;
    public final SaidaPaginacaoService redisService;

    public PaginacaoResponseDTO<SaidaPaginacaoDTO> executar(int page, int limit) {
        int offset = page * limit;

        PaginacaoResponseDTO<SaidaPaginacaoDTO> responseCached = redisService.getCacheadoSaida(page, limit);

        if (responseCached != null) {
            return responseCached;
        }

        PaginacaoResponseDTO<SaidaPaginacaoDTO> response = gateway.buscarPaginadoSaida(offset, limit);

        redisService.salvarSaidaPaginacao(page, limit, response);
        return response;
    }
}
