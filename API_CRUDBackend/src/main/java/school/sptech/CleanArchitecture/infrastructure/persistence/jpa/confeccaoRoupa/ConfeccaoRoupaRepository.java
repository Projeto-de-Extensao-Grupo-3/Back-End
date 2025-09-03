package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public interface ConfeccaoRoupaRepository extends JpaRepository<ConfeccaoRoupaEntity, Integer> {
    boolean existsByRoupaAndTecido(ItemEstoque roupa, ItemEstoque tecido);
}
