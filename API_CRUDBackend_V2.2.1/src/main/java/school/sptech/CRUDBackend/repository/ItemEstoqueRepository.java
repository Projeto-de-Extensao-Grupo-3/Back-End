package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.ItemEstoque;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Integer> {
    boolean existsByDescricao (ItemEstoque tecido);
}
