package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.ConfeccaoRoupaGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntityMapper;

import java.util.List;
import java.util.Set;

@Service
public class ConfeccaoRoupaAdapter implements ConfeccaoRoupaGateway {

    private final ConfeccaoRoupaRepository repository;

    public ConfeccaoRoupaAdapter(ConfeccaoRoupaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<ConfeccaoRoupa> saveAll(Set<ConfeccaoRoupa> confeccaoRoupa) {
        Set<ConfeccaoRoupaEntity> entities = ConfeccaoRoupaEntityMapper.ofDomains(confeccaoRoupa);
        return ConfeccaoRoupaEntityMapper.ofEntities(repository.saveAll(entities));
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteAllByRoupa_IdItemEstoqueEquals(Integer id) {
        repository.deleteAllByRoupa_idItemEstoqueEquals(id);
    }

    @Override
    public boolean existsByRoupaAndTecido(ItemEstoque roupa, ItemEstoque tecido) {
        ItemEstoqueEntity roupaEntity = new ItemEstoqueEntity();
        roupaEntity.setIdItemEstoque(roupa.getIdItemEstoque());
        ItemEstoqueEntity tecidoEntity = new ItemEstoqueEntity();
        tecidoEntity.setIdItemEstoque(tecido.getIdItemEstoque());
        return repository.existsByRoupaAndTecido(roupaEntity, tecidoEntity);
    }
}
