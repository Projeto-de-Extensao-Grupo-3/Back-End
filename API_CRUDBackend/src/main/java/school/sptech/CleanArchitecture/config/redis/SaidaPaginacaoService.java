package school.sptech.CleanArchitecture.config.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueRepository;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.EntradaPaginacaoDTO;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.PaginacaoResponseDTO;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.SaidaPaginacaoDTO;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaidaPaginacaoService {

    private final RedisTemplate<String, Object> redisTemplate;

    public PaginacaoResponseDTO<EntradaPaginacaoDTO> getCacheadoEntrada(int page, int limit) {
        String cacheKey = "entrada:page=" + page + ":limit=" + limit;

        PaginacaoResponseDTO<EntradaPaginacaoDTO> responseCache =
                (PaginacaoResponseDTO<EntradaPaginacaoDTO>)redisTemplate.opsForValue().get(cacheKey);

        if (responseCache != null) {
            System.out.println("RETORNANDO DO CACHE REDIS");
        }

        return responseCache;
    }

    public PaginacaoResponseDTO<SaidaPaginacaoDTO> getCacheadoSaida(int page, int limit) {
        String cacheKey = "saida:page=" + page + ":limit=" + limit;

        PaginacaoResponseDTO<SaidaPaginacaoDTO> responseCache =
                (PaginacaoResponseDTO<SaidaPaginacaoDTO>)redisTemplate.opsForValue().get(cacheKey);

        if (responseCache != null) {
            System.out.println("RETORNANDO DO CACHE REDIS");
        }

        return (PaginacaoResponseDTO<SaidaPaginacaoDTO>) redisTemplate.opsForValue().get(cacheKey);
    }

    public void salvarEntradaPaginacao(int page, int limit, PaginacaoResponseDTO<EntradaPaginacaoDTO> response) {
        String cacheKey = "entrada:page=" + page + ":limit=" + limit;

        redisTemplate
                .opsForValue()
                .set(cacheKey, response, Duration.ofSeconds(30));
    }

    public void salvarSaidaPaginacao(int page, int limit, PaginacaoResponseDTO<SaidaPaginacaoDTO> response) {
        String cacheKey = "saida:page=" + page + ":limit=" + limit;

        redisTemplate
                .opsForValue()
                .set(cacheKey, response, Duration.ofSeconds(30));
    }
}
