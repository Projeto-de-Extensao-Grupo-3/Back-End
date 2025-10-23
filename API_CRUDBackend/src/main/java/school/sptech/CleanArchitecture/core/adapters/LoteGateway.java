package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.infrastructure.web.dto.lote.LoteEmEstoqueDto;

import java.util.List;

public interface LoteGateway {
    Lote save(Lote lote);

    Lote findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    List<Lote> findAll();

    boolean existsByDescricao(String descricao);

    List<LoteEmEstoqueDto> findLotesEmEstoque();
}
