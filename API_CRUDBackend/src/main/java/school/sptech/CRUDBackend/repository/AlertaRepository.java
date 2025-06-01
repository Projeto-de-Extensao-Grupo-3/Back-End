package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.CRUDBackend.entity.Alerta;
import school.sptech.CRUDBackend.entity.ItemEstoque;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Integer> {
    List<Alerta> findByItemEstoque(ItemEstoque itemEstoque);
}
