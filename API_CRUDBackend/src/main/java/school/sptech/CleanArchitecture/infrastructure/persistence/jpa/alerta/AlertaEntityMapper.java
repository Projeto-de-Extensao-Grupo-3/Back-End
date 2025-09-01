package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.alerta;

import school.sptech.CleanArchitecture.core.domain.entity.Alerta;

import java.util.Objects;

public class AlertaEntityMapper {

    public static AlertaEntity ofDomain (Alerta domain){
        if (Objects.isNull(domain)) {
            return null;
        }

        var enitity = new AlertaEntity();

        enitity.setIdAlerta(domain.getIdAlerta());
        enitity.setDescricao(domain.getDescricao());
        enitity.setDataHora(domain.getDataHora());
//        enitity.setItemEstoque(domain.getItemEstoque());

        return enitity;
    }

    public static Alerta ofEntity(AlertaEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        var domain = new Alerta();

        domain.setIdAlerta(entity.getIdAlerta());
        domain.setDescricao(entity.getDescricao());
        domain.setDataHora(entity.getDataHora());
//        domain.setItemEstoque(entity.getItemEstoque());

        return domain;
    }

}
