package school.sptech.CRUDBackend.dto.LoteItemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
