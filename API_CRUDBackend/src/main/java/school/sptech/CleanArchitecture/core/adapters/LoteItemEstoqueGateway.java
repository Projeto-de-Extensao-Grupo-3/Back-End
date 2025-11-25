package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.*;

import java.util.List;

public interface LoteItemEstoqueGateway {
    LoteItemEstoque save(LoteItemEstoque lote);

    LoteItemEstoque findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    List<LoteItemEstoque> findAll();

    LoteItemEstoque buscarPorId(Integer id);

    PaginacaoResponseDTO<EntradaPaginacaoDTO> buscarPaginado(int offset, int limit);

    PaginacaoResponseDTO<SaidaPaginacaoDTO> buscarPaginadoSaida(int offset, int limit);

    List<MargemLucroProdutoDto> buscarMargemLucroProdutos();

    List<PecasMaiorMaoObraDto> buscarPecasMaiorMaoObra();
}
