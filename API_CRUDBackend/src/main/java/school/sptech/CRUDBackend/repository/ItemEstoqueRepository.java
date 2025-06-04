package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.CRUDBackend.entity.ItemEstoque;

import java.util.List;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Integer> {
    Boolean existsByDescricao(String descricao);
    List<ItemEstoque> findByDescricaoContainsIgnoreCase(String descricao);

    @Query("SELECT i FROM ItemEstoque i\n" +
            "JOIN i.categoria s\n" +
            "JOIN s.categoriaPai c\n" +
            "WHERE c.nome = ?1")
    List<ItemEstoque> findByTipo(String tipo);
}
