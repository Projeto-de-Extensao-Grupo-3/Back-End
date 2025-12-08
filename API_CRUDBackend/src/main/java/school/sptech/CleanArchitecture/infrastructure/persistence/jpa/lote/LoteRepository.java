package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.CleanArchitecture.infrastructure.web.dto.lote.LoteDetalhadoDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.lote.LoteEmEstoqueDto;

import java.util.List;

public interface LoteRepository extends JpaRepository<LoteEntity, Integer> {

    boolean existsByDescricao(String descricao);

    @Query(value = "SELECT fk_lote as idLote, descricao as nomeItem, quantidade as qtdItem, fk_item_estoque as idItem, preco as precoItem, id_lote_item_estoque as idLoteItemEstoque, fk_categoria_pai as fkCategoriaPai " +
            "FROM autocomplete_saida", nativeQuery = true)
    List<LoteEmEstoqueDto> findLotesEmEstoque();

    @Query(value = """
            SELECT l.id_lote, l.dt_entrada, ie.descricao, l.descricao as motivo, i.url, lie.qtd_item as 'qtd_entrada', IFNULL(sum(se.qtd_saida), 0) as 'qtd_saida'
            	FROM lote l\s
            		JOIN lote_item_estoque lie
            			ON l.id_lote = lie.fk_lote
            		JOIN item_estoque ie\s
            			ON ie.id_item_estoque = lie.fk_item_estoque
            		LEFT JOIN saida_estoque se\s
            			ON lie.id_lote_item_estoque = se.fk_lote_item_estoque
            		JOIN imagem i
            			ON i.id_imagem = ie.fk_imagem\s
            		GROUP BY l.id_lote, l.dt_entrada, i.url, ie.descricao, lie.qtd_item;
            """, nativeQuery = true)
    List<LoteDetalhadoDto> findLoteDetalhado();
}
