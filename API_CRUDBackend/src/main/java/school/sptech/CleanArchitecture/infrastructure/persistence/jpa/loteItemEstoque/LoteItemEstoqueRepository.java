package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.MargemLucroProdutoDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.PecasMaiorMaoObraDto;

import java.time.LocalDateTime;
import java.util.List;

public interface LoteItemEstoqueRepository extends JpaRepository<LoteItemEstoqueEntity, Integer> {

    @Query(value = """
    SELECT 
        img.url AS url,
        ie.descricao AS nomeItem,
        lie.qtd_item AS qtdItem,
        l.id_lote AS idLote,
        p.nome AS nomeParceiro,
        l.dt_entrada AS dataEntrada
    FROM lote_item_estoque lie
    JOIN item_estoque ie ON ie.id_item_estoque = lie.fk_item_estoque
    LEFT JOIN imagem img ON img.id_imagem = ie.fk_imagem
    JOIN lote l ON l.id_lote = lie.fk_lote
    JOIN parceiro p ON p.id_parceiro = l.fk_parceiro
    ORDER BY l.dt_entrada DESC, ie.id_item_estoque
    LIMIT :limit OFFSET :offset
    """, nativeQuery = true)
    List<Object[]> buscarPaginado(@Param("offset") int offset, @Param("limit") int limit);

    @Query(value = """
        SELECT COUNT(*) 
        FROM lote_item_estoque
        """, nativeQuery = true)
    Long contarTotal();

    @Query(value = """
        SELECT COUNT(*) 
        FROM saida_estoque
        """, nativeQuery = true)
    Long contarTotalSaida();

    @Query(value = """
    SELECT 
        img.url AS url,
        ie.descricao AS nomeItem,
        se.qtd_saida AS qtdItem,
        l.id_lote AS idLote,
        p.nome AS nomeParceiro,
        TIMESTAMP(CONCAT(se.data, ' ', se.hora)) AS saidaEstoque
    FROM lote_item_estoque lie
    JOIN item_estoque ie ON ie.id_item_estoque = lie.fk_item_estoque
    LEFT JOIN imagem img ON img.id_imagem = ie.fk_imagem
    JOIN lote l ON l.id_lote = lie.fk_lote
    JOIN parceiro p ON p.id_parceiro = l.fk_parceiro
    JOIN saida_estoque se ON se.fk_lote_item_estoque = lie.id_lote_item_estoque
    ORDER BY se.data DESC, se.hora DESC, se.id_saida_estoque
    LIMIT :limit OFFSET :offset
    """, nativeQuery = true)
    List<Object[]> buscarSaidaPaginada(@Param("offset") int offset, @Param("limit") int limit);

    @Query(value = """
            SELECT lie_roupa.fk_item_estoque AS id_roupa,\s
                	ie.descricao,
                	ROUND(((ie.preco - ROUND(AVG(lie_roupa.preco / lie_roupa.qtd_item) + cnf.custo_tecidos, 2)) / ie.preco) * 100, 2) as 'margem_lucro_%'
                	FROM lote_item_estoque as lie_roupa
                		JOIN item_estoque as ie
                			ON lie_roupa.fk_item_estoque = ie.id_item_estoque
                		JOIN (SELECT cnf.fk_roupa, SUM(cnf.qtd_tecido * ie.preco) as custo_tecidos FROM confeccao_roupa as cnf JOIN item_estoque as ie ON cnf.fk_tecido = ie.id_item_estoque GROUP BY cnf.fk_roupa) as cnf
                			ON ie.id_item_estoque = cnf.fk_roupa
                		JOIN categoria as c
                			ON ie.fk_categoria = c.id_categoria
                		JOIN lote as l
                			ON lie_roupa.fk_lote = l.id_lote\s
                		LEFT JOIN caracteristica_item_estoque as carac_ie 	
                			ON ie.id_item_estoque = carac_ie.fk_item_estoque
                		LEFT JOIN categoria as carac						
                			ON carac_ie.fk_categoria  = carac.id_categoria
                	WHERE IFNULL(carac.nome, '') LIKE %:caracteristica%
                		AND c.nome LIKE %:categoria%
                		AND l.dt_entrada BETWEEN :dataInicio AND :dataFim
                	GROUP BY lie_roupa.fk_item_estoque, ie.descricao;
        """, nativeQuery = true)
    List<MargemLucroProdutoDto> buscarMargemLucroProdutos(@Param("dataInicio")String dataInicio,@Param("dataFim") String dataFim,@Param("caracteristica") String caracteristica,@Param("categoria") String categoria);

    @Query(value = """
            SELECT lie_roupa.fk_item_estoque,\s
            	truncate(AVG(lie_roupa.preco / lie_roupa.qtd_item), 2) as custo_costureira,
            	truncate(cnf.custo_tecidos, 2) as custo_tecidos,
            	truncate(AVG(lie_roupa.preco / lie_roupa.qtd_item) + cnf.custo_tecidos, 2) as custo_total,
            	ie.preco,
            	truncate(((ie.preco - ROUND(AVG(lie_roupa.preco / lie_roupa.qtd_item) + cnf.custo_tecidos, 2)) / ie.preco) * 100, 2) as 'margem_lucro_%',
            	ie.descricao\s
            	FROM lote_item_estoque as lie_roupa
            		JOIN item_estoque as ie
            			ON lie_roupa.fk_item_estoque = ie.id_item_estoque
            		JOIN categoria as c
            			ON ie.fk_categoria = c.id_categoria
            		JOIN (SELECT cnf.fk_roupa, SUM(cnf.qtd_tecido * ie.preco) as custo_tecidos FROM confeccao_roupa as cnf JOIN item_estoque as ie ON cnf.fk_tecido = ie.id_item_estoque GROUP BY cnf.fk_roupa) as cnf
            			ON ie.id_item_estoque = cnf.fk_roupa\s
            				JOIN lote as l
            			ON lie_roupa.fk_lote = l.id_lote\s
            		LEFT JOIN caracteristica_item_estoque as carac_ie 	
            			ON ie.id_item_estoque = carac_ie.fk_item_estoque
            		LEFT JOIN categoria as carac						
            			ON carac_ie.fk_categoria  = carac.id_categoria
            	WHERE IFNULL(carac.nome, '') LIKE %:caracteristica%
            		AND c.nome LIKE %:categoria%
            		AND l.dt_entrada BETWEEN :dataInicio AND :dataFim
            	GROUP BY lie_roupa.fk_item_estoque, ie.descricao, ie.preco;""", nativeQuery = true)
    List<PecasMaiorMaoObraDto> buscarPecasMaiorMaoObra(@Param("dataInicio")String dataInicio,@Param("dataFim") String dataFim,@Param("caracteristica") String caracteristica,@Param("categoria") String categoria);
}