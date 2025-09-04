package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira;

import jakarta.validation.Valid;
import school.sptech.CleanArchitecture.core.application.command.prateleira.CriarPrateleiraCommand;
import school.sptech.CleanArchitecture.core.application.command.prateleira.PrateleiraAtualizarCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;
import school.sptech.CleanArchitecture.infrastructure.web.dto.prateleira.PrateleiraRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.prateleira.PrateleiraResponseDto;

import java.util.Objects;

public class PrateleiraEntityMapper {

    public static PrateleiraEntity ofDomain (Prateleira domain){
        if (Objects.isNull(domain)) {
            return null;
        }
        var entity = new PrateleiraEntity();
        entity.setIdPrateleira(domain.getIdPrateleira());
        entity.setCodigo(domain.getCodigo());
        return entity;
    }

    public static Prateleira ofEntity(PrateleiraEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        var domain = new Prateleira();
        domain.setIdPrateleira(entity.getIdPrateleira());
        domain.setCodigo(entity.getCodigo());
        return domain;
    }

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
