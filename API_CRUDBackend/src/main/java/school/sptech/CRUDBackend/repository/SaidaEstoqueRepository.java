package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.SaidaEstoque;

import java.util.List;

public interface SaidaEstoqueRepository extends JpaRepository<SaidaEstoque, Integer> {

    List<SaidaEstoque> findByMotivoSaida(String motivo);
}
