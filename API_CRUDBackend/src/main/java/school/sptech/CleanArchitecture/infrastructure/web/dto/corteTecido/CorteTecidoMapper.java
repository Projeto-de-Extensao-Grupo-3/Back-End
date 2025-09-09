package school.sptech.CleanArchitecture.infrastructure.web.dto.corteTecido;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import school.sptech.CleanArchitecture.core.application.command.corteTecido.AtualizarCorteTecidoCommand;
import school.sptech.CleanArchitecture.core.application.command.corteTecido.CriarCorteTecidoCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.corteTecido.CorteTecidoEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs CorteTecido.")
@RequiredArgsConstructor
public class CorteTecidoMapper {

    public static CriarCorteTecidoCommand toCriarCommand(CorteTecidoRequestDto dto) {
        return new CriarCorteTecidoCommand(
                dto.getInicio(),
                dto.getTermino(),
                dto.getFuncionario().getIdFuncionario(),
                dto.getLoteItemEstoque().getIdLoteItemEstoque()
        );
    }

    public static AtualizarCorteTecidoCommand toAtualizarCommand(Integer id, CorteTecidoRequestDto dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(dto.getFuncionario().getIdFuncionario());

        LoteItemEstoque loteItemEstoque = new LoteItemEstoque();
        loteItemEstoque.setIdLoteItemEstoque(dto.getLoteItemEstoque().getIdLoteItemEstoque());

        return new AtualizarCorteTecidoCommand(
                id,
                dto.getInicio(),
                dto.getTermino(),
                funcionario,
                loteItemEstoque
        );
    }

    public static CorteTecidoCadastroDto toCadastroDto(CorteTecidoEntity corteTecido) {
        return new CorteTecidoCadastroDto(
                corteTecido.getInicio(),
                corteTecido.getTermino()
        );
    }

    public static CorteTecidoResponseDto toResponseDto(CorteTecidoEntity corteTecido) {
        FuncionarioEntity funcionario = corteTecido.getFuncionario();
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

    public static List<CorteTecidoResponseDto> toResponseDtos(List<CorteTecidoEntity> cortesTecido) {
        return cortesTecido
                .stream()
                .map(CorteTecidoMapper::toResponseDto)
                .toList();
    }
}
