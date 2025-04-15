package school.sptech.CRUDBackend.dto.corteTecido;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.CorteTecido;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs CorteTecido.")
public class CorteTecidoMapper {
    public static CorteTecido toEntity(CorteTecidoRequestDto requestDto) {
        return new CorteTecido(
                null,
                requestDto.getInicio(),
                requestDto.getTermino()
        );
    }

    public static CorteTecidoResponseDto toResponseDto(CorteTecido corteTecido) {
        return new CorteTecidoResponseDto(
                corteTecido.getId(),
                corteTecido.getInicio(),
                corteTecido.getTermino()
        );
    }

    public static List<CorteTecidoResponseDto> toResponseDtos(List<CorteTecido> cortesTecido) {
        return cortesTecido
                .stream()
                .map(CorteTecidoMapper::toResponseDto)
                .toList();
    }
}
