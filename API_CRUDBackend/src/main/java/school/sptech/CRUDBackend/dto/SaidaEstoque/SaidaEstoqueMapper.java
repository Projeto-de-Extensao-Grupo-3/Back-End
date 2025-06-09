package school.sptech.CRUDBackend.dto.SaidaEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.entity.SaidaEstoque;
import school.sptech.CRUDBackend.entity.Parceiro;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs SaidaEstoque")
public class SaidaEstoqueMapper {

    public static SaidaEstoque toEntity(SaidaEstoqueRequestDto requestDto){
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(requestDto.getResponsavel().getIdFuncionario());
        LoteItemEstoque loteItemEstoque = new LoteItemEstoque();
        loteItemEstoque.setIdLoteItemEstoque(requestDto.getLoteItemEstoque().getIdLoteItemEstoque());
        Parceiro costureira = null;
        if (requestDto.getCostureira() != null) {
            costureira = new Parceiro();
            costureira.setIdParceiro(requestDto.getCostureira().getIdCostureira());
        }
        return new SaidaEstoque(
                null,
                requestDto.getData(),
                requestDto.getHora(),
                requestDto.getQtdSaida(),
                requestDto.getMotivoSaida(),
                funcionario,
                loteItemEstoque,
                costureira
        );
    }

    public static SaidaEstoqueResponseDto toResponseDto(SaidaEstoque saidaEstoque){
//        Funcionario funcionario = saidaEstoque.getResponsavel();
//        SaidaEstoqueFuncionarioResponseDto funcionarioDto = new SaidaEstoqueFuncionarioResponseDto(
//                funcionario.getNome(), funcionario.getEmail()
//        );
//        LoteItemEstoque loteItemEstoque = saidaEstoque.getLoteItemEstoque();
//        SaidaEstoqueLoteItemEstoqueResponseDto loteItemEstoqueDto = new SaidaEstoqueLoteItemEstoqueResponseDto(
//                loteItemEstoque.getQtdItem(), loteItemEstoque.getPreco()
//        );
//        ServicoTerceiro costureira = saidaEstoque.getCostureira();
//        SaidaEstoqueCostureiraResponseDto costureiraDto =
//                costureira != null ?
//                new SaidaEstoqueCostureiraResponseDto(
//                    costureira.getNome(), costureira.getTelefone(), costureira.getEmail()
//                )
//                : null;
        return new SaidaEstoqueResponseDto(
                saidaEstoque.getIdSaidaEstoque(),
                saidaEstoque.getData(),
                saidaEstoque.getHora(),
                saidaEstoque.getQtdSaida(),
                saidaEstoque.getMotivoSaida()
        );
    }

    public static List<SaidaEstoqueResponseDto> toResponseDtos(List<SaidaEstoque> saidas){
        return saidas
                .stream()
                .map(SaidaEstoqueMapper::toResponseDto)
                .toList();
    }

    public static SaidaEstoqueCadastroDto toCadastroDto(SaidaEstoque saidaEstoque) {
        return new SaidaEstoqueCadastroDto(
                saidaEstoque.getIdSaidaEstoque(),
                saidaEstoque.getData(),
                saidaEstoque.getHora(),
                saidaEstoque.getQtdSaida(),
                saidaEstoque.getMotivoSaida()
        );
    }
}
