package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;

public interface ConfeccaoRoupaRepository extends JpaRepository<ConfeccaoRoupaEntity, Integer> {
    boolean existsByRoupaAndTecido(ItemEstoqueEntity roupa, ItemEstoqueEntity tecido);

    void deleteAllByRoupa_idItemEstoqueEquals(Integer id);
}
