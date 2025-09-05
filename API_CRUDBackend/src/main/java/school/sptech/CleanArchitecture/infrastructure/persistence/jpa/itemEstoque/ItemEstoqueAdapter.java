package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemEstoqueAdapter implements ItemEstoqueGateway {

    private final ItemEstoqueRepository repository;

    public ItemEstoqueAdapter(ItemEstoqueRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemEstoque save(ItemEstoque itemEstoque) {
        ItemEstoqueEntity entity = ItemEstoqueEntityMapper.ofDomain(itemEstoque);
        return ItemEstoqueEntityMapper.ofEntity(repository.save(entity));
    }

    @Override
    public List<ItemEstoque> findAll() {
        return repository.findAll().stream()
                .map(ItemEstoqueEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<ItemEstoque> findById(Integer id) {
        return repository.findById(id)
                .map(ItemEstoqueEntityMapper::ofEntity);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Boolean existsByDescricao(String descricao) {
        return repository.existsByDescricao(descricao);
    }

    @Override
    public List<ItemEstoque> findByTipo(String tipo) {
        return repository.findByTipo(tipo).stream()
                .map(ItemEstoqueEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemEstoque> findByDescricaoContainsIgnoreCase(String descricao) {
        return repository.findByDescricaoContainsIgnoreCase(descricao).stream()
                .map(ItemEstoqueEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Double calcularCustoProducao(Integer id) {
        return repository.calcularCustoProducao(id);
    }
}
