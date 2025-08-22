package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.CRUDBackend.entity.Funcionario;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByEmail(String email);
    Optional<Funcionario> findByEmailAndSenha(String email, String senha); // para teste sem token, depois apagar
    Boolean existsByCpfOrEmail(String cpf, String email);
}
