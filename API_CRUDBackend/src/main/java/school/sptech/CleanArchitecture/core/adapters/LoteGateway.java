package school.sptech.CleanArchitecture.core.adapters;



import school.sptech.CleanArchitecture.core.domain.entity.Lote;

import java.util.List;

public interface LoteGateway {
    Lote save(Lote lote);

    Lote findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    List<Lote> findAll();

    boolean existsByDescricao(String descricao);
}
