package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<LoteEntity, Integer> {

    boolean existsByDescricao(String descricao);
}
