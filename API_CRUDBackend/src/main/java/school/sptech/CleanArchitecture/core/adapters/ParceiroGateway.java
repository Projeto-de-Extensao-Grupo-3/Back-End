package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

import java.util.List;
import java.util.Optional;

public interface ParceiroGateway {

    Parceiro save(Parceiro parceiro);

    boolean existsById(Integer id);

    Optional<Parceiro> findById(Integer id);

    List<Parceiro> findAll();

    Parceiro findByNome(String nome);

    void deleteById(Integer id);

    Boolean existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(
            String email, String identificacao, String endereco
    );

    List<Parceiro> findByCategoriaOrderByIdParceiroDesc(String categoria);

    List<Parceiro> findByCategoriaAndNomeContainsIgnoreCase(String categoria, String nome);
}
