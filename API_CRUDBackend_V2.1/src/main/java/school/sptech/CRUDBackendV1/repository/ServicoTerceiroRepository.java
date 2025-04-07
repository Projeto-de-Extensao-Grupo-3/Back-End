package school.sptech.CRUDBackendV1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackendV1.entity.ServicoTerceiro;

public interface ServicoTerceiroRepository extends JpaRepository<ServicoTerceiro, Integer> {
    Boolean existsByEmailOrIdentificacaoOrEnderecoIgnoreCaseAll(
            String email, String identificacao, String endereco);
}
