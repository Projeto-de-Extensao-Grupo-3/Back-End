package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.saidaEstoque;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SaidaEstoqueRepository extends JpaRepository<SaidaEstoqueEntity, Integer> {

    List<SaidaEstoqueEntity> findByMotivoSaida(String motivo);

    List<SaidaEstoqueEntity> findByData(LocalDate data);
}
