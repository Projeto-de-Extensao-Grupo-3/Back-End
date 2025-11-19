package school.sptech.CleanArchitecture.config.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueRepository;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.EntradaPaginacaoDTO;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.PaginacaoResponseDTO;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.SaidaPaginacaoDTO;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaidaPaginacaoService {

    private final LoteItemEstoqueRepository repository;
    private final RedisTemplate<String, Object> redisTemplate;

    public void salvarEntradaPaginacao(int page, int limit,
                                PaginacaoResponseDTO<EntradaPaginacaoDTO> response) {

        String cacheKey = "entrada:page=" + page + ":limit=" + limit;

        redisTemplate
                .opsForValue()
                .set(cacheKey, response, 30);

        System.out.println("SALVANDO NO CACHE REDIS");
    }

    public void salvarSaidaPaginacao(int page, int limit,
                                       PaginacaoResponseDTO<SaidaPaginacaoDTO> response) {

        String cacheKey = "saida:page=" + page + ":limit=" + limit;

        redisTemplate
                .opsForValue()
                .set(cacheKey, response, 30);

        System.out.println("SALVANDO NO CACHE REDIS");
    }
}
