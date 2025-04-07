package school.sptech.CRUDBackendV1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackendV1.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Funcionario findByEmailAndSenha(String email, String senha);
    Boolean existsByCpfOrEmail(String cpf, String email);
}
