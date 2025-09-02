package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {

    boolean existsByNome(String nome);
    CategoriaEntity findByNome(String nome);

    @Query(value = "SELECT f.* FROM Categoria AS p\n" +
            "JOIN Categoria f\n" +
            "ON f.fk_categoria_pai = p.id_categoria\n" +
            "AND p.nome = ?1", nativeQuery = true)
    List<CategoriaEntity> findByTipo(String tipo);
}
