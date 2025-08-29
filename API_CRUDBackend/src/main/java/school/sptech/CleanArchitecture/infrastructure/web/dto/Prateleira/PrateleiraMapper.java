package school.sptech.CleanArchitecture.infrastructure.web.dto.Prateleira;


import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

public class PrateleiraMapper {

    public static PrateleiraResponseDto toResponseDto(Prateleira prateleira) {
        return new PrateleiraResponseDto(prateleira.getIdPrateleira(), prateleira.getCodigo());
    }
}
