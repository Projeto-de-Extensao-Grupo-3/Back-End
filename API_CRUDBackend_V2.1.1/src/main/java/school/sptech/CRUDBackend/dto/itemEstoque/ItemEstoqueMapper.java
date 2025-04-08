package school.sptech.CRUDBackend.dto.itemEstoque;

import school.sptech.CRUDBackend.entity.ItemEstoque;

public class ItemEstoqueMapper {
    public static ItemEstoque toEntity(ItemEstoqueRequestDto requestDto) {
        ItemEstoque item = new ItemEstoque();
        item.setCategoria(requestDto.getCategoria());
        item.setDescricao(requestDto.getDescricao());
        item.setComplemento(requestDto.getComplemento());
        item.setPeso(requestDto.getPeso());
        item.setQtdMinima(requestDto.getQtdMinima());
        item.setQtdArmazenado(requestDto.getQtdArmazenado());
        return item;
    }

    public static ItemEstoqueResponseDto toResponseDto(ItemEstoque item) {
        ItemEstoqueResponseDto responseDto = new ItemEstoqueResponseDto();
        responseDto.setId(item.getId());
        responseDto.setCategoria(item.getCategoria());
        responseDto.setDescricao(item.getDescricao());
        responseDto.setComplemento(item.getComplemento());
        responseDto.setPeso(item.getPeso());
        responseDto.setQtdMinima(item.getQtdMinima());
        responseDto.setQtdArmazenado(item.getQtdArmazenado());
        return responseDto;
    }
}
