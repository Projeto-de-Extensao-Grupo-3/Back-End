package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.saidaEstoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque.TaxaDefeitoCosturaDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SaidaEstoqueRepository extends JpaRepository<SaidaEstoqueEntity, Integer> {

    List<SaidaEstoqueEntity> findByMotivoSaida(String motivo);

    List<SaidaEstoqueEntity> findByData(LocalDate data);

    @Query(value = """
            SELECT
               p.nome AS costureira,
               COUNT(CASE WHEN se.motivo_saida LIKE '%defeito%' THEN 1 END) AS qtd_defeitos,
               COUNT(se.id_saida_estoque) AS qtd_total_entregas,
               ROUND(
                   (COUNT(CASE WHEN se.motivo_saida LIKE '%defeito%' THEN 1 END) /
                    COUNT(se.id_saida_estoque)) * 100, 2
               ) AS taxa_defeito_percentual
            FROM saida_estoque AS se
            LEFT JOIN parceiro AS p ON se.fk_costureira = p.id_parceiro
            WHERE se.data BETWEEN :dataInicio AND :dataFim
            AND se.fk_costureira IS NOT NULL
            GROUP BY p.nome
            ORDER BY taxa_defeito_percentual DESC;
            """, nativeQuery = true)
    List<TaxaDefeitoCosturaDto> calcularTaxaDefeitoCostura(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);

    List<SaidaEstoqueEntity> data(LocalDate data);
}
