package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoqueEntity, Integer> {
    Boolean existsByDescricao(String descricao);
    List<ItemEstoqueEntity> findByDescricaoContainsIgnoreCase(String descricao);

    @Query("SELECT i FROM ItemEstoque i\n" +
            "JOIN i.categoria s\n" +
            "JOIN s.categoriaPai c\n" +
            "WHERE c.nome = ?1")
    List<ItemEstoqueEntity> findByTipo(String tipo);

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
}
