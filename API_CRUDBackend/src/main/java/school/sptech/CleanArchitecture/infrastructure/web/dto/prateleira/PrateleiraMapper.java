package school.sptech.CleanArchitecture.infrastructure.web.dto.prateleira;


import jakarta.validation.Valid;
import school.sptech.CleanArchitecture.core.application.command.prateleira.CriarPrateleiraCommand;
import school.sptech.CleanArchitecture.core.application.command.prateleira.PrateleiraAtualizarCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

public class PrateleiraMapper {

    public static PrateleiraResponseDto toResponseDto(Prateleira prateleira) {
        return new PrateleiraResponseDto(prateleira.getIdPrateleira(), prateleira.getCodigo());
    }

    public static CriarPrateleiraCommand toCriarCommand(PrateleiraRequestDto dto) {
        return new CriarPrateleiraCommand(dto.getCodigo());
    }

    public static PrateleiraAtualizarCommand toAtualizarCommand(Integer id, @Valid PrateleiraRequestDto dto) {
        return new PrateleiraAtualizarCommand(id, dto.getCodigo());
    }
}
