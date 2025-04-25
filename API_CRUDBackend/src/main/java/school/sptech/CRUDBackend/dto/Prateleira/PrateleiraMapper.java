package school.sptech.CRUDBackend.dto.Prateleira;

import school.sptech.CRUDBackend.entity.Prateleira;

public class PrateleiraMapper {

    public static Prateleira toEntity(PrateleiraRequestDto dto) {
        Prateleira estante = new Prateleira();
        estante.setCodigo(dto.getCodigo());
        return estante;
    }

    public static PrateleiraResponseDto toResponseDto(Prateleira prateleira) {
        return new PrateleiraResponseDto(prateleira.getId(), prateleira.getCodigo());
    }
}
