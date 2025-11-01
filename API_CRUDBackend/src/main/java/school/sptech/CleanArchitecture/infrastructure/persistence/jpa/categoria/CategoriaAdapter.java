package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.categoria.CategoriaNaoEncontradaException;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaAdapter implements CategoriaGateway {

    private final CategoriaRepository repository;

    public CategoriaAdapter(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity categoriaEntity = CategoriaEntityMapper.ofDomain(categoria);
        CategoriaEntity categoriaSalvaNoBanco = repository.save(categoriaEntity);
        return CategoriaEntityMapper.ofEntity(categoriaSalvaNoBanco);
    }

    @Override
    public List<Categoria> findAll() {
        return repository.findAll()
                .stream()
                .map(CategoriaEntityMapper::ofEntity)
                .collect(Collectors.toList());
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
    public void delete(Categoria categoria) {
        CategoriaEntity categoriaEntity = CategoriaEntityMapper.ofDomain(categoria);
        repository.delete(categoriaEntity);
    }

    @Override
    public Categoria findById(Integer id) {
        CategoriaEntity categoriaEntity = repository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria com ID " + id + " não encontrada"));
        return CategoriaEntityMapper.ofEntity(categoriaEntity);
    }

    @Override
    public boolean existsByNome(String nome) {
        return repository.existsByNome(nome);
    }

    @Override
    public List<Categoria> findByTipo(String tipo) {
        return repository.findByTipo(tipo)
                .stream()
                .map(CategoriaEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Categoria findByNome(String nome) {
        return CategoriaEntityMapper.ofEntity(repository.findByNome(nome));
    }
}
