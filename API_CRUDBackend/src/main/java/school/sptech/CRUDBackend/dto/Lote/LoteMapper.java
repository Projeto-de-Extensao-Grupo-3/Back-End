package school.sptech.CRUDBackend.dto.Lote;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.Lote;
import school.sptech.CRUDBackend.entity.Parceiro;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs Lote.")
public class LoteMapper {

    public static Lote toEntity(LoteRequestDto requestDto){
        Parceiro parceiro = new Parceiro();
        parceiro.setIdParceiro(requestDto.getParceiro().getIdParceiro());
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(requestDto.getResponsavel().getIdFuncionario());
        return new Lote(
                null,
                requestDto.getDescricao(),
                requestDto.getDataEntrada(),
                parceiro,
                funcionario
        );
    }

    public static LoteResponseDto toResponseDto(Lote lote){
        Funcionario funcionario = lote.getResponsavel();
        LoteFuncionarioResponseDto funcionarioDto = new LoteFuncionarioResponseDto(
                funcionario.getNome(), funcionario.getTelefone(), funcionario.getEmail()
        );
        Parceiro parceiro = lote.getParceiro();
        LoteParceiroResponseDto parceiroDto = new LoteParceiroResponseDto(
                parceiro.getCategoria(), parceiro.getNome(),
                parceiro.getTelefone(), parceiro.getEmail()
        );
        return new LoteResponseDto(
                lote.getIdLote(),
                lote.getDescricao(),
                lote.getDataEntrada(),
                funcionarioDto,
                parceiroDto
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
