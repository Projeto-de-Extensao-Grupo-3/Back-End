package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.ConfeccaoRoupa;
import school.sptech.CRUDBackend.entity.ItemEstoque;

public interface ConfeccaoRoupaRepository extends JpaRepository<ConfeccaoRoupa, Integer> {
    boolean existsByRoupaAndTecido(ItemEstoque roupa, ItemEstoque tecido);
}
