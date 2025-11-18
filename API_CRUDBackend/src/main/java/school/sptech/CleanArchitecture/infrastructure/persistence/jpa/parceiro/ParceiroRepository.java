package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParceiroRepository extends JpaRepository<ParceiroEntity, Integer> {
    Boolean existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(String email, String identificacao, String endereco);
    ParceiroEntity findByNome(String nome);
    List<ParceiroEntity> findByCategoriaAndIdentificacaoIsNotNullOrderByIdParceiroDesc(String categoria);
    List<ParceiroEntity> findByCategoriaAndNomeContainsIgnoreCaseAndIdentificacaoIsNotNull(String categoria, String nome);
}
