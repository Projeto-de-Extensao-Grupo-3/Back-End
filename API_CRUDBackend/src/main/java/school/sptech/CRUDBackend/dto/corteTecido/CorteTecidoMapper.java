package school.sptech.CRUDBackend.dto.corteTecido;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import school.sptech.CRUDBackend.entity.CorteTecido;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs CorteTecido.")
@RequiredArgsConstructor
public class CorteTecidoMapper {

    public static CorteTecido toEntity(CorteTecidoRequestDto requestDto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(requestDto.getFuncionario().getIdFuncionario());
        LoteItemEstoque loteItemEstoque = new LoteItemEstoque();
        loteItemEstoque.setIdLoteItemEstoque(requestDto.getLoteItemEstoque().getIdLoteItemEstoque());
        return new CorteTecido(
                null,
                requestDto.getInicio(),
                requestDto.getTermino(),
                funcionario,
                loteItemEstoque
        );
    }

    public static CorteTecidoCadastroDto toCadastroDto(CorteTecido corteTecido) {
        return new CorteTecidoCadastroDto(
                corteTecido.getInicio(),
                corteTecido.getTermino()
        );
    }

    public static CorteTecidoResponseDto toResponseDto(CorteTecido corteTecido) {
        Funcionario funcionario = corteTecido.getFuncionario();
        CorteTecidoFuncionarioResponseDto funcionarioResponseDto = new CorteTecidoFuncionarioResponseDto(
                funcionario.getNome(), funcionario.getTelefone(), funcionario.getEmail()
        );
        return new CorteTecidoResponseDto(
                corteTecido.getIdCorteTecido(),
                corteTecido.getInicio(),
                corteTecido.getTermino(),
                funcionarioResponseDto
        );
    }

    public static List<CorteTecidoResponseDto> toResponseDtos(List<CorteTecido> cortesTecido) {
        return cortesTecido
                .stream()
                .map(CorteTecidoMapper::toResponseDto)
                .toList();
    }
}
