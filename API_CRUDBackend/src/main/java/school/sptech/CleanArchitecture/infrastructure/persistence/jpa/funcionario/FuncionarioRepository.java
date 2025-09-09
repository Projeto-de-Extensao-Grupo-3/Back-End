
package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {
    Optional<FuncionarioEntity> findByEmail(String email);
    boolean existsByCpfOrEmail(String cpf, String email);
}
