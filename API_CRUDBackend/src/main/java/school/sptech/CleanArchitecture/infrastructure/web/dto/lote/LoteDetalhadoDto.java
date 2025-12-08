package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
public class LoteDetalhadoDto {
    private Integer idLote;
    private Timestamp dtEntrada;
    private String descricao;
    private String motivo;
    private String url;
    private Double qtdEntrada;
    private Double qtdSaida;
}
