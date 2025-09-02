package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PrateleiraRepository extends JpaRepository<PrateleiraEntity, Integer> {

    boolean existsByCodigo(String codigo);

    PrateleiraEntity findByCodigo(String codigo);
}
