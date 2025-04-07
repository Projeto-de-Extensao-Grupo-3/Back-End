package school.sptech.CRUDBackendV1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackendV1.entity.ItemEstoque;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Integer> {
    boolean existsByDescricao (ItemEstoque tecido);
}
