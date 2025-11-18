package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.Categoria;

import java.util.List;

public interface CategoriaGateway {

    Categoria save(Categoria categoria);

    List<Categoria> findAll();

    boolean existsById(Integer id);

    void deleteById(Integer id);

    void delete(Categoria categoria);

    Categoria findById(Integer id);

    boolean existsByNome(String nome);

    List<Categoria> findByTipo(String tipo);

    Categoria findByNome(String nome);
}
