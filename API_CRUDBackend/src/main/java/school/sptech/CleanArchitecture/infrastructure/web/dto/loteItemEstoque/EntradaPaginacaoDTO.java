package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class EntradaPaginacaoDTO {
    private String url;
    private String nomeItem;
    private Double qtdItem;
    private Integer idLote;
    private String nomeParceiro;
    private Timestamp dataEntrada;
    private String motivo;
}
