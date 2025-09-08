package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.corteTecido;

import school.sptech.CleanArchitecture.core.adapters.CorteTecidoGateway;
import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;

import java.util.List;

public class CorteTecidoAdapter implements CorteTecidoGateway {
    private final CorteTecidoRepository repository;

    public CorteTecidoAdapter(CorteTecidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public CorteTecido save(CorteTecido corteTecido) {
        CorteTecidoEntity entity = CorteTecidoEntityMapper.ofDomain(corteTecido);
        CorteTecidoEntity savedEntity = repository.save(entity);
        return CorteTecidoEntityMapper.ofEntity(savedEntity);
    }

    @Override
    public CorteTecido findById(Integer id) {
        CorteTecidoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corte de Tecido com ID " + id + " não encontrado"));
        return CorteTecidoEntityMapper.ofEntity(entity);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public List<CorteTecido> findAll() {
        return repository.findAll()
                .stream()
                .map(CorteTecidoEntityMapper::ofEntity)
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
