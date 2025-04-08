package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Funcionario findByEmailAndSenha(String email, String senha);
    Boolean existsByCpfOrEmail(String cpf, String email);
}
