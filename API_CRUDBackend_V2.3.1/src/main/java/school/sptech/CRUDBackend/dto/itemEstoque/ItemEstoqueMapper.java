package school.sptech.CRUDBackend.dto.itemEstoque;

import school.sptech.CRUDBackend.entity.ItemEstoque;

import java.util.List;

public class ItemEstoqueMapper {
    public static ItemEstoque toEntity(ItemEstoqueRequestDto requestDto) {
        return new ItemEstoque(
                null,
                requestDto.getCategoria(),
                requestDto.getDescricao(),
                requestDto.getComplemento(),
                requestDto.getPeso(),
                requestDto.getQtdMinima(),
                requestDto.getQtdArmazenado()
        );
    }

    public static ItemEstoqueResponseDto toResponseDto(ItemEstoque item) {
        return new ItemEstoqueResponseDto(
                item.getId(),
                item.getCategoria(),
                item.getDescricao(),
                item.getComplemento(),
                item.getPeso(),
                item.getQtdMinima(),
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
