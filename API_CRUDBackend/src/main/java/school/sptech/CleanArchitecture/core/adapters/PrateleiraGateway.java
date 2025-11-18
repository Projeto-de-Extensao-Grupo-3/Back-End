package school.sptech.CleanArchitecture.core.adapters;


import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

import java.util.List;
import java.util.Optional;

public interface PrateleiraGateway {

    Prateleira save(Prateleira prateleira);

    List<Prateleira> findAll();

    boolean existsById(Integer id);

    void deleteById(Integer id);

    Prateleira findById(Integer id);

    boolean existsByCodigo(String codigo);

    Prateleira findByCodigo(String codigo);
}
