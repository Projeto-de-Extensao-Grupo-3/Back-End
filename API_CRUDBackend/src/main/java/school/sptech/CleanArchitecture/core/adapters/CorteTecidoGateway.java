package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;

public interface CorteTecidoGateway {

    CorteTecido save(CorteTecido corteTecido);

    CorteTecido findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);
}
