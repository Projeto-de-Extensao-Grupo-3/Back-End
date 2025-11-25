package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.ProdutoBaixoGiroDto;

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

    @Query(value =
            "SELECT     ie.descricao AS produto,     COALESCE(SUM(se.qtd_saida), 0) AS total_vendido,     COUNT(se.id_saida_estoque) AS qtd_vendas,     ie.qtd_armazenado AS estoque_atual,     CASE         WHEN MAX(se.data) IS NULL THEN 'Nunca vendeu'         ELSE CONCAT(DATEDIFF(CURDATE(), MAX(se.data)), ' dias')     END AS dias_sem_vender,     CASE         WHEN MAX(se.data) IS NULL THEN 'CRÍTICO - Nunca vendeu'         WHEN DATEDIFF(CURDATE(), MAX(se.data)) > 60 THEN 'CRÍTICO - Liquidar'         WHEN DATEDIFF(CURDATE(), MAX(se.data)) > 30 THEN 'ATENÇÃO - Promoção'         ELSE 'OK'     END AS status_recomendacao FROM item_estoque AS ie LEFT JOIN lote_item_estoque AS lie ON ie.id_item_estoque = lie.fk_item_estoque LEFT JOIN saida_estoque AS se ON lie.id_lote_item_estoque = se.fk_lote_item_estoque     AND se.motivo_saida LIKE '%venda%'     AND se.data >= '2025-03-01' WHERE ie.id_item_estoque BETWEEN 1 AND 22 GROUP BY ie.id_item_estoque, ie.descricao, ie.qtd_armazenado ORDER BY total_vendido ASC,          CASE WHEN MAX(se.data) IS NULL THEN 9999 ELSE DATEDIFF(CURDATE(), MAX(se.data)) END DESC LIMIT 5;",
            nativeQuery = true)
    List<ProdutoBaixoGiroDto> buscarProdutosGiroBaixo();

}
