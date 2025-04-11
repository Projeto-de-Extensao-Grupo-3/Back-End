package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.Lote;

public interface LoteRepository extends JpaRepository<Lote, Integer> {

    boolean existsByDescricao(String descricao);
}
