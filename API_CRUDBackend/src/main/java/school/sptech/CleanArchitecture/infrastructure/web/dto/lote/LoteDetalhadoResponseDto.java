package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteDetalhadoResponseDto {
    private Integer idLote;
    private Timestamp dtLote;
    private String motivo;
    private List<LoteDetalhadoItemDto> itens;

    public LoteDetalhadoResponseDto(Integer idLote, Timestamp dtLote, String motivo) {
        this.idLote = idLote;
        this.dtLote = dtLote;
        this.motivo = motivo;
        this.itens = new ArrayList<>();
    }
}
