package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteEmEstoqueDto {
    private Integer idLote;
    private String nomeItem;
    private Double qtdItem;
    private Integer idItem;
    private Double precoItem;
    private Integer idLoteItemEstoque;
}
