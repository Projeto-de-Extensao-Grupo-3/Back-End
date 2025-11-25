package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.saidaEstoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque.TaxaDefeitoCosturaDto;

import java.time.LocalDate;
import java.util.List;

public interface SaidaEstoqueRepository extends JpaRepository<SaidaEstoqueEntity, Integer> {

    List<SaidaEstoqueEntity> findByMotivoSaida(String motivo);

    List<SaidaEstoqueEntity> findByData(LocalDate data);

    @Query(value = """
            SELECT     p.nome AS costureira,     COUNT(CASE WHEN se.motivo_saida LIKE '%defeito%' THEN 1 END) AS qtd_defeitos,     COUNT(se.id_saida_estoque) AS qtd_total_entregas FROM saida_estoque AS se LEFT JOIN parceiro AS p ON se.fk_costureira = p.id_parceiro WHERE se.fk_costureira IS NOT NULL   AND se.data >= '2025-03-01' GROUP BY p.nome  having qtd_defeitos > 0;""", nativeQuery = true)
    List<TaxaDefeitoCosturaDto> calcularTaxaDefeitoCostura();
}
