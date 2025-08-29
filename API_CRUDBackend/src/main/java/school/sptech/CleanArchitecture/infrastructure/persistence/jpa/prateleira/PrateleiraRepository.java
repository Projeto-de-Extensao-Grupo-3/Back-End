package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;
import java.util.Optional;

public interface PrateleiraRepository extends JpaRepository<PrateleiraEntity, Integer> {

    boolean existsByCodigo(String codigo);

    Optional<Prateleira> findByCodigo(String codigo);
}
