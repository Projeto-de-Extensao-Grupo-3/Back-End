
package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioGateway {
    Funcionario save(Funcionario funcionario);
    Optional<Funcionario> findByEmail(String email);
    Optional<Funcionario> findById(Integer id);
    List<Funcionario> findAllByOrderByIdFuncionarioDesc();
    boolean existsByCpfOrEmail(String cpf, String email);
    void deleteById(Integer id);
    boolean existsById(Integer integer);

    List<Funcionario> findByNomeContainsIgnoreCase(String nome);
    List<Funcionario> findByPermissoes_IdPermissao(Integer idPermissao);

}
