package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.Parceiro;

public interface ParceiroRepository extends JpaRepository<Parceiro, Integer> {
    Boolean existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(
            String email, String identificacao, String endereco
    );
}
