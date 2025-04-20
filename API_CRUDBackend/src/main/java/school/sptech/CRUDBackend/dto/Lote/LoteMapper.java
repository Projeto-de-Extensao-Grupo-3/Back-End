package school.sptech.CRUDBackend.dto.Lote;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.Lote;
import school.sptech.CRUDBackend.entity.ServicoTerceiro;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs Lote.")
public class LoteMapper {

    public static Lote toEntity(LoteRequestDto requestDto){
        ServicoTerceiro servicoTerceiro = new ServicoTerceiro();
        servicoTerceiro.setIdServicoTerceiro(requestDto.getServicoTerceiro().getIdServicoTerceiro());
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(requestDto.getResponsavel().getIdFuncionario());
        return new Lote(
                null,
                requestDto.getDescricao(),
                requestDto.getDataEntrada(),
                servicoTerceiro,
                funcionario
        );
    }

    public static LoteResponseDto toResponseDto(Lote lote){
        Funcionario funcionario = lote.getResponsavel();
        LoteFuncionarioResponseDto funcionarioDto = new LoteFuncionarioResponseDto(
                funcionario.getNome(), funcionario.getTelefone(), funcionario.getEmail()
        );
        ServicoTerceiro servicoTerceiro = lote.getServicoTerceiro();
        LoteServicoTerceiroResponseDto servicoTerceiroDto = new LoteServicoTerceiroResponseDto(
                servicoTerceiro.getCategoria(), servicoTerceiro.getNome(),
                servicoTerceiro.getTelefone(), servicoTerceiro.getEmail()
        );
        return new LoteResponseDto(
                lote.getIdLote(),
                lote.getDescricao(),
                lote.getDataEntrada(),
                funcionarioDto,
                servicoTerceiroDto
        );
    }

    public static List<LoteResponseDto> toResponseDtos(List<Lote> lotes) {
        return lotes
                .stream()
                .map(LoteMapper::toResponseDto)
                .toList();
    }

    public static LoteCadastroDto toCadastroDto(LoteRequestDto requestDto) {
        return new LoteCadastroDto(
                requestDto.getDescricao(),
                requestDto.getDataEntrada()
        );
    }
}
