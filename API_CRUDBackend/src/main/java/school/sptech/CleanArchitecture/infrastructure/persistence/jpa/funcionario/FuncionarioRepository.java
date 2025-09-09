
package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {
    Optional<FuncionarioEntity> findByEmail(String email);
    List<FuncionarioEntity> findByNomeContainsIgnoreCase(String nome);
    Boolean existsByCpfOrEmail(String cpf, String email);
}
