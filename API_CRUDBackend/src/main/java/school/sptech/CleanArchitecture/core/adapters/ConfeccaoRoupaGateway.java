package school.sptech.CleanArchitecture.core.adapters;


import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.Set;

public interface ConfeccaoRoupaGateway {

    Set<ConfeccaoRoupa> saveAll(Set<ConfeccaoRoupa> confeccaoRoupa);

    boolean existsById(Integer id);

    void deleteAllByRoupa_IdItemEstoqueEquals(Integer id);

    boolean existsByRoupaAndTecido(ItemEstoque roupa, ItemEstoque tecido);
}
