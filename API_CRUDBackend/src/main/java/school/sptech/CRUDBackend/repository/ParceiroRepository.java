package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.Parceiro;

import java.util.List;

public interface ParceiroRepository extends JpaRepository<Parceiro, Integer> {
    Boolean existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(
            String email, String identificacao, String endereco
    );

    List<Parceiro> findByCategoria(String categoria);
    List<Parceiro> findByCategoriaAndNomeContainsIgnoreCase(String categoria, String nome);
}
