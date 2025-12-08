package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class EntradaPaginacaoDTO {
    private String url;
    private String nomeItem;
    private Double qtdItem;
    private Integer idLote;
    private String nomeParceiro;
    private Timestamp dataEntrada;
    private String motivo;

    public EntradaPaginacaoDTO(String url, String nomeItem, Double qtdItem, Integer idLote, String nomeParceiro, Timestamp dataEntrada, String motivo, String bucket) {
        this.url = "https://" + bucket + ".s3.us-east-1.amazonaws.com/" + url;
        this.nomeItem = nomeItem;
        this.qtdItem = qtdItem;
        this.idLote = idLote;
        this.nomeParceiro = nomeParceiro;
        this.dataEntrada = dataEntrada;
        this.motivo = motivo;
    }
}
