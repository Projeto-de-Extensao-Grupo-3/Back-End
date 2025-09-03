package school.sptech.CleanArchitecture.infrastructure.web.dto.confeccaoRoupa;


import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaRoupaCommand;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaTecidoCommand;
import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

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

    public static ConfeccaoRoupaCadastrarCommand toCadastrarCommand(ConfeccaoRoupaRequestDto dto) {
        ConfeccaoRoupaRoupaCommand roupa = new ConfeccaoRoupaRoupaCommand(dto.getRoupa().getIdRoupa());

        ConfeccaoRoupaTecidoCommand tecido = new ConfeccaoRoupaTecidoCommand(dto.getTecido().getIdTecido());

        return new ConfeccaoRoupaCadastrarCommand(
                roupa,
                tecido,
                dto.getQtdTecido()
        );
    }

    public static ConfeccaoRoupaAtualizarCommand toAtualizarCommand(Integer id, ConfeccaoRoupaRequestDto dto){
        ConfeccaoRoupaRoupaCommand roupa = new ConfeccaoRoupaRoupaCommand(dto.getRoupa().getIdRoupa());

        ConfeccaoRoupaTecidoCommand tecido = new ConfeccaoRoupaTecidoCommand(dto.getTecido().getIdTecido());

        return new ConfeccaoRoupaAtualizarCommand(
                id,
                roupa,
                tecido,
                dto.getQtdTecido()
        );
    }
}
