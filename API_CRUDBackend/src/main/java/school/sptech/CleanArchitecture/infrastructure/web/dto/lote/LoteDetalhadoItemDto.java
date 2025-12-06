package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteDetalhadoItemDto {
    private String nomeItem;
    private String url;
    private Double qtdEntrada;
    private Double qtdSaida;
}
