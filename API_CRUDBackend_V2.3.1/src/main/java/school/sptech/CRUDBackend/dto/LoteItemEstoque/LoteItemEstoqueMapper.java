package school.sptech.CRUDBackend.dto.LoteItemEstoque;

import school.sptech.CRUDBackend.entity.LoteItemEstoque;

public class LoteItemEstoqueMapper {

    public static LoteItemEstoque toEntity(LoteItemEstoqueRequestDto requestDto){
        LoteItemEstoque loteItemEstoque = new LoteItemEstoque();
        loteItemEstoque.setQtdItem(requestDto.getQtdItem());
        loteItemEstoque.setPreco(requestDto.getPreco());

        return loteItemEstoque;
    }

    public static LoteItemEstoqueResponseDto toResponseDto(LoteItemEstoque loteItemEstoque){
            LoteItemEstoqueResponseDto responseDto = new LoteItemEstoqueResponseDto();
            responseDto.setId(loteItemEstoque.getId());
            responseDto.setQtdItem(loteItemEstoque.getQtdItem());
            responseDto.setPreco(loteItemEstoque.getPreco());
            return responseDto;
    }
}
