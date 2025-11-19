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

    public PaginacaoResponseDTO<EntradaPaginacaoDTO> listarEntradaPaginado(int page, int limit) {
        String cacheKey = "entrada:page=" + page + ":limit=" + limit;

        // 1. Verifica se existe no Redis
        PaginacaoResponseDTO<EntradaPaginacaoDTO> cached =
                (PaginacaoResponseDTO<EntradaPaginacaoDTO>) redisTemplate.opsForValue().get(cacheKey);

        if (cached != null) {
            System.out.println("RETORNANDO DO CACHE REDIS");
            return cached;
        }

        // 2. Caso não exista no cache → consulta no banco
        int offset = page * limit;
        List<Object[]> resultados = repository.buscarPaginado(offset, limit);

        List<EntradaPaginacaoDTO> itens = resultados.stream()
                .map(obj -> new EntradaPaginacaoDTO(
                        (String) obj[0],
                        (String) obj[1],
                        ((Number) obj[2]).doubleValue(),
                        (Integer) obj[3],
                        (String) obj[4],
                        (Timestamp) obj[5]
                ))


                .toList();

        // 3. Busca total de registros para paginação
        long total = repository.contarTotalSaida(); // Certifique-se que existe esse método

        // 4. Monta objeto de paginação
        PaginacaoResponseDTO<EntradaPaginacaoDTO> response = new PaginacaoResponseDTO<>();
        response.setConteudo(itens);
        response.setLimite(limit);
        response.setPaginaAtual(offset);
        response.setTotalRegistros(total);

        // 5. Salva no Redis com TTL de 30 segundos
        redisTemplate.opsForValue().set(cacheKey, response);

        return response;
    }

    public PaginacaoResponseDTO<SaidaPaginacaoDTO> listarPaginado(int page, int limit) {
        String cacheKey = "saida:page=" + page + ":limit=" + limit;

        // 1. Verifica se existe no Redis
        PaginacaoResponseDTO<SaidaPaginacaoDTO> cached =
                (PaginacaoResponseDTO<SaidaPaginacaoDTO>) redisTemplate.opsForValue().get(cacheKey);

        if (cached != null) {
            System.out.println("RETORNANDO DO CACHE REDIS");
            return cached;
        }

        // 2. Caso não exista no cache → consulta no banco
        int offset = page * limit;
        List<Object[]> resultados = repository.buscarSaidaPaginada(offset, limit);

        List<SaidaPaginacaoDTO> itens = resultados.stream()
                .map(obj -> new SaidaPaginacaoDTO(
                        (String) obj[0],
                        (String) obj[1],
                        ((Number) obj[2]).doubleValue(),
                        (Integer) obj[3],
                        (String) obj[4],
                        (Timestamp) obj[5]
                ))
                .toList();

        // 3. Busca total de registros para paginação
        long total = repository.contarTotalSaida(); // Certifique-se que existe esse método

        // 4. Monta objeto de paginação
        PaginacaoResponseDTO<SaidaPaginacaoDTO> response = new PaginacaoResponseDTO<>();
        response.setConteudo(itens);
        response.setLimite(limit);
        response.setPaginaAtual(offset);
        response.setTotalRegistros(total);

        // 5. Salva no Redis com TTL de 30 segundos
        redisTemplate.opsForValue().set(cacheKey, response);

        return response;
    }
}
