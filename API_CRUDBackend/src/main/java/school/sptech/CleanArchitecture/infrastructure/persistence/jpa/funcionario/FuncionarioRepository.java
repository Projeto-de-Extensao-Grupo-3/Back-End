
package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {
    Optional<FuncionarioEntity> findByEmail(String email);
    List<FuncionarioEntity> findByNomeContainsIgnoreCaseAndCpfIsNotNull(String nome);
    List<FuncionarioEntity> findByCpfIsNotNullOrderByIdFuncionarioDesc();
    Boolean existsByCpfOrEmail(String cpf, String email);
    List<FuncionarioEntity> findByPermissoes_IdPermissao(Integer idPermissao);
}
