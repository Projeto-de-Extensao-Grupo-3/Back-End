package school.sptech.CleanArchitecture.core.adapters;


import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public interface ConfeccaoRoupaGateway {

    ConfeccaoRoupa save(ConfeccaoRoupa confeccaoRoupa);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    boolean existsByRoupaAndTecido(ItemEstoque roupa, ItemEstoque tecido);
}
