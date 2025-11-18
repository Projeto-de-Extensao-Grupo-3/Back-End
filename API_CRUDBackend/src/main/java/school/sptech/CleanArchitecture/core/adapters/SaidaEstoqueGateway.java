package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SaidaEstoqueGateway {

    SaidaEstoque save(SaidaEstoque saidaEstoque);

    List<SaidaEstoque> findAll();

    Optional<SaidaEstoque> findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    List<SaidaEstoque> findByMotivoSaida(String motivo);

    List<SaidaEstoque> findByData(LocalDate data);
}
