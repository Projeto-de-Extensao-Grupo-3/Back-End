package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.Parceiroaa;

import java.util.List;

public interface ParceiroRepository extends JpaRepository<Parceiroaa, Integer> {
    Boolean existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(
            String email, String identificacao, String endereco
    );

    List<Parceiroaa> findByCategoria(String categoria);
    List<Parceiroaa> findByCategoriaAndNomeContainsIgnoreCase(String categoria, String nome);
}
