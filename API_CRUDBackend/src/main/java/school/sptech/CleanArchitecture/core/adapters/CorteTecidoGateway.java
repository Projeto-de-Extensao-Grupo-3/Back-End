package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;

import java.util.List;

public interface CorteTecidoGateway {

    CorteTecido save(CorteTecido corteTecido);

    CorteTecido findById(Integer id);

    boolean existsById(Integer id);

    List<CorteTecido> findAll();

    void deleteById(Integer id);
}
