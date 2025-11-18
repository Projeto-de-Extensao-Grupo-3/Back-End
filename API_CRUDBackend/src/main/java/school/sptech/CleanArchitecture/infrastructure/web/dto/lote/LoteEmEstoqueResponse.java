package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoteEmEstoqueResponse {
    private Integer idLote;
    private String nomeItem;
    private Double qtdItem;
    private Integer idItem;
    private Double precoItem;
    private Integer idLoteItemEstoque;
    private Boolean isRoupa;
}
