package school.sptech.CRUDBackend.dto.LoteItemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.entity.Lote;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs LoteItemEstoque.")
public class LoteItemEstoqueMapper {

    public static LoteItemEstoque toEntity(LoteItemEstoqueRequestDto requestDto){
        ItemEstoque itemEstoque = new ItemEstoque();
        itemEstoque.setIdItemEstoque(requestDto.getItemEstoque().getIdItemEstoque());
        Lote lote = new Lote();
        lote.setIdLote(requestDto.getLote().getIdLote());
        return new LoteItemEstoque(
                null,
                requestDto.getQtdItem(),
                requestDto.getPreco(),
                itemEstoque,
                lote
        );
    }

    public static LoteItemEstoqueResponseDto toResponseDto(LoteItemEstoque loteItemEstoque) {
        ItemEstoque itemEstoque = loteItemEstoque.getItemEstoque();
        LoteItemEstoqueItemResponseDto itemEstoqueDto = new LoteItemEstoqueItemResponseDto(
                itemEstoque.getIdItemEstoque(), itemEstoque.getDescricao(), itemEstoque.getQtdArmazenado()
        );
        Lote lote = loteItemEstoque.getLote();
        LoteItemEstoqueLoteResponseDto loteDto = new LoteItemEstoqueLoteResponseDto(
                lote.getIdLote(), lote.getDescricao(), lote.getDataEntrada()
        );
        return new LoteItemEstoqueResponseDto(
                loteItemEstoque.getIdLoteItemEstoque(),
                loteItemEstoque.getQtdItem(),
                loteItemEstoque.getPreco(),
                itemEstoqueDto,
                loteDto
        );
    }

    public static List<LoteItemEstoqueResponseDto> toResponseDtos(List<LoteItemEstoque> lotesItemEstoque) {
        return lotesItemEstoque
                .stream()
                .map(LoteItemEstoqueMapper::toResponseDto)
                .toList();
    }

    public static LoteItemEstoqueCadastroDto toCadastroDto(LoteItemEstoque loteItemEstoque) {
        return new LoteItemEstoqueCadastroDto(
                loteItemEstoque.getIdLoteItemEstoque(),
                loteItemEstoque.getQtdItem(),
                loteItemEstoque.getPreco()
        );
    }
}
