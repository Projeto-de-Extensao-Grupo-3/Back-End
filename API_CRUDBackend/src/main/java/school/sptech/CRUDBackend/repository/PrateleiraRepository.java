package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.Prateleira;

import java.util.Optional;

public interface PrateleiraRepository extends JpaRepository<Prateleira, Integer> {

    boolean existsByCodigo(String codigo);

   Optional<Prateleira> findByCodigo(String codigo);
}
