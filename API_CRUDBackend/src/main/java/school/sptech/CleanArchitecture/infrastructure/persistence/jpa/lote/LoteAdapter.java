package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.LoteGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;

import java.util.List;

@Service
public class LoteAdapter implements LoteGateway {

    private final LoteRepository repository;

    public LoteAdapter(LoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Lote save(Lote lote) {
        LoteEntity entity = LoteEntityMapper.ofCads(lote); //ofDomain
        LoteEntity savedEntity = repository.save(entity);
        return LoteEntityMapper.ofCadsEntity(savedEntity);
    }

    @Override
    public Lote findById(Integer id) {
        LoteEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote com ID " + id + " não encontrado"));
        return LoteEntityMapper.ofEntity(entity);
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
    public List<Lote> findAll() {
        return repository.findAll()
                .stream()
                .map(LoteEntityMapper::ofEntity)
                .toList();
    }

    @Override
    public boolean existsByDescricao(String descricao) {
        return repository.existsByDescricao(descricao);
    }
}
