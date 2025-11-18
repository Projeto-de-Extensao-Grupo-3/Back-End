package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.CleanArchitecture.infrastructure.web.dto.lote.LoteEmEstoqueDto;

import java.util.List;

public interface LoteRepository extends JpaRepository<LoteEntity, Integer> {

    boolean existsByDescricao(String descricao);

    @Query(value = "SELECT fk_lote as idLote, descricao as nomeItem, quantidade as qtdItem, fk_item_estoque as idItem, preco as precoItem, id_lote_item_estoque as idLoteItemEstoque, fk_categoria_pai as fkCategoriaPai " +
            "FROM autocomplete_saida", nativeQuery = true)
    List<LoteEmEstoqueDto> findLotesEmEstoque();

}
