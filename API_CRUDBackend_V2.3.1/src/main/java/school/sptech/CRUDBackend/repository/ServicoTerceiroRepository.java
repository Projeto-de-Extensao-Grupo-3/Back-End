package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.ServicoTerceiro;

public interface ServicoTerceiroRepository extends JpaRepository<ServicoTerceiro, Integer> {
    Boolean existsByEmailOrIdentificacaoOrEnderecoIgnoreCaseAll(
            String email, String identificacao, String endereco
    );
}
