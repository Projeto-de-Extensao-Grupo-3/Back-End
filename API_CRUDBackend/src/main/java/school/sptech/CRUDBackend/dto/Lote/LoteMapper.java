package school.sptech.CRUDBackend.dto.Lote;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.Lote;

@Schema(description = "Classe de mapeamento de DTOs Lote.")
public class LoteMapper {

    public static Lote toEntity(LoteRequestDto requestDto){
        Lote lote = new Lote();
        lote.setDescricao(requestDto.getDescricao());
        lote.setDataEntrada(requestDto.getDataEntrada());

        return lote;
    }

    public static LoteResponseDto toResponseDto(Lote lote){
        LoteResponseDto responseDto = new LoteResponseDto();
        responseDto.setIdLote(lote.getIdLote());
        responseDto.setDescricao(lote.getDescricao());
        responseDto.setDataEntrada(lote.getDataEntrada());

        return responseDto;
    }
}
