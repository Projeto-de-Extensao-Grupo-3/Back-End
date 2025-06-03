package school.sptech.CRUDBackend.dto.confeccaoRoupa;

import school.sptech.CRUDBackend.entity.ConfeccaoRoupa;
import school.sptech.CRUDBackend.entity.ItemEstoque;

public class ConfeccaoRoupaMapper {

    public static ConfeccaoRoupa toEntity(ConfeccaoRoupaRequestDto requestDto) {

        ConfeccaoRoupaRoupaRequestDto roupaRequestDto = requestDto.getRoupa();
        ItemEstoque roupa = new ItemEstoque();
        roupa.setIdItemEstoque(roupaRequestDto.getIdRoupa());

        ConfeccaoRoupaTecidoRequestDto tecidoRequestDto = requestDto.getTecido();
        ItemEstoque tecido = new ItemEstoque();
        tecido.setIdItemEstoque(tecidoRequestDto.getIdTecido());

        return new ConfeccaoRoupa(
                null,
                roupa,
                tecido,
                requestDto.getQtdTecido()
        );
    }

    public static ConfeccaoRoupaResponseDto toResponseDto(ConfeccaoRoupa confeccaoRoupa) {
        return new ConfeccaoRoupaResponseDto(
                confeccaoRoupa.getIdConfeccaoRoupa()
        );
    }
}
