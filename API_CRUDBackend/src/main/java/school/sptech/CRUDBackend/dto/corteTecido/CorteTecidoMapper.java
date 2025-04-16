package school.sptech.CRUDBackend.dto.corteTecido;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.CorteTecido;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs CorteTecido.")
public class CorteTecidoMapper {
    public static CorteTecido toEntity(CorteTecidoRequestDto requestDto) {
        Funcionario funcionario = new Funcionario(requestDto.getFuncionario().getIdFuncionario(),
                null, null, null, null, null, null);
        LoteItemEstoque loteItemEstoque = new LoteItemEstoque(requestDto.getLoteItemEstoque().getId(),
                null, null);
        return new CorteTecido(
                null,
                requestDto.getInicio(),
                requestDto.getTermino(),
                funcionario,
                null
        );
    }

    public static CorteTecidoResponseDto toResponseDto(CorteTecido corteTecido) {
        return new CorteTecidoResponseDto(
                corteTecido.getIdCorteTecido(),
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
