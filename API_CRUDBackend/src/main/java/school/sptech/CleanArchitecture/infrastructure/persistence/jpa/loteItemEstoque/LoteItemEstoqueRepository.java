package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    LIMIT :limit OFFSET :offset
    """, nativeQuery = true)
    List<Object[]> buscarPaginado(@Param("offset") int offset, @Param("limit") int limit);

    @Query(value = """
        SELECT COUNT(*) 
        FROM lote_item_estoque
        """, nativeQuery = true)
    Long contarTotal();

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
    LIMIT :limit OFFSET :offset
    """, nativeQuery = true)
    List<Object[]> buscarSaidaPaginada(@Param("offset") int offset, @Param("limit") int limit);

}
