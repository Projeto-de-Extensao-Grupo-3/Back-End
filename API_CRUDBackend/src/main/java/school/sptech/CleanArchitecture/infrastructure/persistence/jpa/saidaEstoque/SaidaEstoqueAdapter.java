package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.saidaEstoque;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaidaEstoqueAdapter implements SaidaEstoqueGateway {

    private final SaidaEstoqueRepository repository;

    public SaidaEstoqueAdapter(SaidaEstoqueRepository repository) {
        this.repository = repository;
    }

    @Override
    public SaidaEstoque save(SaidaEstoque saidaEstoque) {
        SaidaEstoqueEntity saidaEstoqueParaSalvar = SaidaEstoqueEntityMapper.toEntity(saidaEstoque);
        SaidaEstoqueEntity saidaSalvaNoBanco = repository.save(saidaEstoqueParaSalvar);
        return SaidaEstoqueEntityMapper.toDomain(saidaSalvaNoBanco);
    }

    @Override
    public List<SaidaEstoque> findAll() {
        return repository.findAll().stream()
                .map(SaidaEstoqueEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SaidaEstoque> findById(Integer id) {
        return repository.findById(id)
                .map(SaidaEstoqueEntityMapper::toDomain);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<SaidaEstoque> findByMotivoSaida(String motivo) {
        return repository.findByMotivoSaida(motivo).stream()
                .map(SaidaEstoqueEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaidaEstoque> findByData(LocalDate data) {
        return repository.findByData(data).stream()
                .map(SaidaEstoqueEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
