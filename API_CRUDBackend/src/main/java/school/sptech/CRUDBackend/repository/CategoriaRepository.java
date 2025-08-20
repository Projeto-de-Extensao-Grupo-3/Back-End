package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.CRUDBackend.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    boolean existsByNome(String nome);
    Optional<Categoria> findByNome(String nome);

    @Query(value = "SELECT f.* FROM Categoria AS p\n" +
            "JOIN Categoria f\n" +
            "ON f.fk_categoria_pai = p.id_categoria\n" +
            "AND p.nome = ?1", nativeQuery = true)
    List<Categoria> findByTipo(String tipo);
}
