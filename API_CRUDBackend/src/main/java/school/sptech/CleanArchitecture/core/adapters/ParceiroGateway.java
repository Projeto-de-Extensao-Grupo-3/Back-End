package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

import java.util.List;

public interface ParceiroGateway {

    Parceiro save(Parceiro parceiro);

    boolean existsById(Integer id);

    Parceiro findById(Integer id);

    List<Parceiro> findAll();

    Parceiro findByNome(String nome);

    void deleteById(Integer id);

    Boolean existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(
            String email, String identificacao, String endereco
    );

    List<Parceiro> findByCategoria(String categoria);

    List<Parceiro> findByCategoriaAndNomeContainsIgnoreCase(String categoria, String nome);
}
