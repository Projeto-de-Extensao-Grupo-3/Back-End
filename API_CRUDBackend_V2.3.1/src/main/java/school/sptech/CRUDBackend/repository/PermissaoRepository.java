package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {
    Boolean existsByDescricao(String descricao);
}
