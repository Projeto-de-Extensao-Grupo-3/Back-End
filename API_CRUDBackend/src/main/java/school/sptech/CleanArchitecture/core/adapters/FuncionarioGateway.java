package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioGateway {
    Funcionario save(Funcionario lote);

    Funcionario findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    List<Funcionario> findAll();

    Optional<Funcionario> findByEmail(String email);
    List<Funcionario> findByNomeContainsIgnoreCase(String nome);
    Boolean existsByCpfOrEmail(String cpf, String email);
    Optional<Funcionario> findByEmailAndSenha(String email, String senha);
}
