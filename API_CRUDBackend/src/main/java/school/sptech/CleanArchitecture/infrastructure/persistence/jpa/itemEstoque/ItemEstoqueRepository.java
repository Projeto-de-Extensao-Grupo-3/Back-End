package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.DefeitosPorRoupaDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.EvolucaoVendasDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.ProdutoBaixoGiroDto;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoqueEntity, Integer> {
    Boolean existsByDescricao(String descricao);
    List<ItemEstoqueEntity> findByDescricaoContainsIgnoreCaseAndCategoria_CategoriaPai_Nome(String descricao, String tipo);

//    @Query("SELECT i FROM ItemEstoqueEntity i\n" +
//            "JOIN i.categoria s\n" +
//            "JOIN s.categoriaPai c\n" +
//            "WHERE c.nome = ?1")
    List<ItemEstoqueEntity> findByCategoria_CategoriaPai_NomeOrderByIdItemEstoqueDesc(String tipo);

    @Query(value =
            "SELECT SUM(c.qtd_tecido * tecido.preco + lt_item.preco) AS CUSTO_TOTAL\n" +
                    "\tFROM item_estoque AS roupa \n" +
                    "\tJOIN confeccao_roupa AS c \n" +
                    "\t\tON roupa.id_item_estoque = roupa_id_item_estoque\n" +
                    "\tJOIN item_estoque AS tecido \n" +
                    "\t\tON tecido.id_item_estoque = tecido_id_item_estoque\n" +
                    "\tJOIN lote_item_estoque AS lt_item\n" +
                    "\t\tON lt_item.id_item_estoque = roupa.id_item_estoque\n" +
                    "\tJOIN lote\n" +
                    "\t\tON lote.id_lote = lt_item.id_lote\n" +
                    "\tWHERE lote.data_entrada = (\n" +
                    "\t\tSELECT MAX(data_entrada) FROM lote JOIN lote_item_estoque\n" +
                    "\t\t\tON lote.id_lote = lt_item.id_lote\n" +
                    "            WHERE lote_item_estoque.id_item_estoque = ?1)\n" +
                    "\tAND lt_item.id_item_estoque = ?1 \n" +
                    "GROUP BY roupa.id_item_estoque\n",
            nativeQuery = true)
    Double calcularCustoProducao(Integer id);

    List<ItemEstoqueEntity> findByCategoria(CategoriaEntity categoria);

    List<ItemEstoqueEntity> findByCaracteristicas_IdCategoria(Integer idCategoria);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM caracteristica_item_estoque WHERE fk_categoria = :idCategoria", nativeQuery = true)
    void removerCaracteristica(@Param("idCategoria") Integer idCategoria);

    @Query(value = """
            SELECT
            ie.descricao AS produto,
            COALESCE(SUM(se.qtd_saida), 0) AS total_vendido,
    COUNT(se.id_saida_estoque) AS qtd_vendas,
    ie.qtd_armazenado AS estoque_atual,
    DATEDIFF(CURDATE(), MAX(se.data)) AS dias_sem_vender,
            CASE
    WHEN DATEDIFF(CURDATE(), MAX(se.data)) > 60 THEN 'CRÍTICO - Liquidar'
    WHEN DATEDIFF(CURDATE(), MAX(se.data)) > 30 THEN 'ATENÇÃO - Promoção'
    ELSE 'OK'
    END AS status_recomendacao
    FROM item_estoque AS ie
    LEFT JOIN lote_item_estoque AS lie ON ie.id_item_estoque = lie.fk_item_estoque
    LEFT JOIN saida_estoque AS se ON lie.id_lote_item_estoque = se.fk_lote_item_estoque
    AND se.motivo_saida LIKE '%venda%'
    AND se.data >= '2025-03-01'
    JOIN categoria as c
    ON ie.fk_categoria = c.id_categoria
    LEFT JOIN caracteristica_item_estoque as carac_ie
    ON ie.id_item_estoque = carac_ie.fk_item_estoque
    LEFT JOIN categoria as carac
    ON carac_ie.fk_categoria  = carac.id_categoria
    WHERE IFNULL(carac.nome, '') LIKE %:caracteristica%
    AND c.nome LIKE %:categoria%
    AND c.fk_categoria_pai = 2
    GROUP BY ie.id_item_estoque, ie.descricao, ie.qtd_armazenado
    ORDER BY total_vendido ASC, dias_sem_vender DESC
    LIMIT 5;""",
            nativeQuery = true)
    List<ProdutoBaixoGiroDto> buscarProdutosGiroBaixo(@Param("caracteristica") String caracteristica,@Param("categoria") String categoria);

    @Query(value = """
                SELECT
                ie.descricao AS roupa,
                COUNT(CASE WHEN se.motivo_saida LIKE '%defeito%' THEN 1 END) AS qtd_defeitos,
                COUNT(se.id_saida_estoque) AS qtd_total_saidas,
                ROUND(
                    (COUNT(CASE WHEN se.motivo_saida LIKE '%defeito%' THEN 1 END) /
                     COUNT(se.id_saida_estoque)) * 100, 2
                ) AS taxa_defeito_percentual
            FROM saida_estoque AS se
            JOIN lote_item_estoque AS lie ON se.fk_lote_item_estoque = lie.id_lote_item_estoque
            JOIN item_estoque AS ie ON ie.id_item_estoque = lie.fk_item_estoque
            JOIN categoria as c ON ie.fk_categoria = c.id_categoria
            JOIN lote as l ON lie.fk_lote = l.id_lote\s
            LEFT JOIN caracteristica_item_estoque as carac_ie ON ie.id_item_estoque = carac_ie.fk_item_estoque
            LEFT JOIN categoria as carac ON carac_ie.fk_categoria  = carac.id_categoria
            WHERE IFNULL(carac.nome, '') LIKE %:caracteristica%
            	AND c.nome LIKE %:categoria%
            	AND l.dt_entrada BETWEEN :dataInicio AND :dataFim
            GROUP BY ie.descricao
            HAVING qtd_defeitos > 0
            ORDER BY taxa_defeito_percentual DESC;""",
            nativeQuery = true)
    List<DefeitosPorRoupaDto> buscarDefeitosPorProduto(@Param("dataInicio") String dataInicio, @Param("dataFim") String dataFim, @Param("caracteristica") String caracteristica, @Param("categoria") String categoria);

    @Query(value = """
            SELECT DATE_FORMAT(vendas.data, '%Y-%m') as periodo,
            	truncate(SUM(ie.preco * qtd_saida), 2) as faturamento_bruto,
            	truncate(SUM(margem_lucro.margem * ie.preco * qtd_saida), 2) as lucro,
            	truncate (SUM(ie.preco * qtd_saida - margem_lucro.margem * ie.preco * qtd_saida), 2) as custos
            	FROM saida_estoque as vendas\s
            		JOIN lote_item_estoque as lie\s
            			ON vendas.fk_lote_item_estoque = lie.id_lote_item_estoque
            		JOIN item_estoque as ie\s
            			ON lie.fk_item_estoque = ie.id_item_estoque
            		JOIN (SELECT ie.id_item_estoque as id,\s
            				ROUND(((ie.preco - ROUND(AVG(lie_roupa.preco / lie_roupa.qtd_item) + cnf.custo_tecidos, 2)) / ie.preco) , 2) as 'margem',\s
            				ie.descricao\s
            					FROM lote_item_estoque as lie_roupa
            						JOIN item_estoque as ie\s
            							ON lie_roupa.fk_item_estoque = ie.id_item_estoque
            						JOIN categoria as c\s
            							ON ie.fk_categoria = c.id_categoria
            						JOIN (SELECT cnf.fk_roupa,\s
            							SUM(cnf.qtd_tecido * ie.preco) as custo_tecidos\s
            							FROM confeccao_roupa as cnf\s
            								JOIN item_estoque as ie\s
            									ON cnf.fk_tecido = ie.id_item_estoque\s
            								GROUP BY cnf.fk_roupa) as cnf
            							ON ie.id_item_estoque = cnf.fk_roupa\s
            						WHERE c.fk_categoria_pai = 2
            						GROUP BY lie_roupa.fk_item_estoque,\s
            						ie.descricao,\s
            						ie.preco) as margem_lucro\s
            			ON ie.id_item_estoque = margem_lucro.id
            		JOIN categoria as c
            			ON ie.fk_categoria = c.id_categoria
            		JOIN lote as l
            			ON lie.fk_lote = l.id_lote\s
            		LEFT JOIN caracteristica_item_estoque as carac_ie 	
            			ON ie.id_item_estoque = carac_ie.fk_item_estoque
            		LEFT JOIN categoria as carac						
            			ON carac_ie.fk_categoria  = carac.id_categoria
            	WHERE IFNULL(carac.nome, '') LIKE %:caracteristica%
            		AND c.nome LIKE %:categoria%
            		AND vendas.fk_costureira IS NULL
            		AND DATE_FORMAT(vendas.data, '%Y-%m') BETWEEN :dataInicio AND :dataFim
            	GROUP BY periodo;""",
            nativeQuery = true)
    List<EvolucaoVendasDto> buscarEvolucaoVendas(@Param("dataInicio") String dataInicio, @Param("dataFim") String dataFim, @Param("caracteristica") String caracteristica, @Param("categoria") String categoria);
}
