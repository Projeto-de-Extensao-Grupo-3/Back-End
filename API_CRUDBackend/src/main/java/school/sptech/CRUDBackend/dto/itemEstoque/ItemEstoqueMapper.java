package school.sptech.CRUDBackend.dto.itemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.ItemEstoque;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs ItemEstoque.")
public class ItemEstoqueMapper {
    public static ItemEstoque toEntity(ItemEstoqueRequestDto requestDto) {
        return new ItemEstoque(
                null,
                requestDto.getCategoria(),
                requestDto.getDescricao(),
                requestDto.getComplemento(),
                requestDto.getPeso(),
                requestDto.getQtdMinimo(),
                requestDto.getQtdArmazenado()
        );
    }

    public static ItemEstoqueResponseDto toResponseDto(ItemEstoque item) {
        return new ItemEstoqueResponseDto(
                item.getIdItemEstoque(),
                item.getCategoria(),
                item.getDescricao(),
                item.getComplemento(),
                item.getPeso(),
                item.getQtdMinimo(),
                item.getQtdArmazenado()
        );
    }

    public static List<ItemEstoqueResponseDto> toResponseDtos(List<ItemEstoque> itensEstoque) {
        return itensEstoque
                .stream()
                .map(ItemEstoqueMapper::toResponseDto)
                .toList();
    }
}
