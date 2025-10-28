
package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.permissao.PermissaoEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {
    Optional<FuncionarioEntity> findByEmail(String email);
    List<FuncionarioEntity> findByNomeContainsIgnoreCase(String nome);
    List<FuncionarioEntity> findAllByOrderByIdFuncionarioDesc();
    Boolean existsByCpfOrEmail(String cpf, String email);
    List<FuncionarioEntity> findByPermissoes_IdPermissao(Integer idPermissao);
}
