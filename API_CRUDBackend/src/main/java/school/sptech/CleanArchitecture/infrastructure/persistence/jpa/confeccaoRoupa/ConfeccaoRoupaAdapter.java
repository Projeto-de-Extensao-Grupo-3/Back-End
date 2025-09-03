package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.ConfeccaoRoupaGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

@Service
public class ConfeccaoRoupaAdapter implements ConfeccaoRoupaGateway {

    private final ConfeccaoRoupaRepository repository;

    public ConfeccaoRoupaAdapter(ConfeccaoRoupaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConfeccaoRoupa save(ConfeccaoRoupa confeccaoRoupa) {
        ConfeccaoRoupaEntity entity = ConfeccaoRoupaEntityMapper.ofDomain(confeccaoRoupa);
        return ConfeccaoRoupaEntityMapper.ofEntity(repository.save(entity));
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
    public boolean existsByRoupaAndTecido(ItemEstoque roupa, ItemEstoque tecido) {
        return repository.existsByRoupaAndTecido(roupa, tecido);
    }
}
