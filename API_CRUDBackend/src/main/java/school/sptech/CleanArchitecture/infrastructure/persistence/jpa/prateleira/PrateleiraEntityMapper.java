package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira;

import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.alerta.AlertaEntity;

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

}
