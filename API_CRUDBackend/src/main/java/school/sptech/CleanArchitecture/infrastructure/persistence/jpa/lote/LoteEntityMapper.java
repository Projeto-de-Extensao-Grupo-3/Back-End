package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote;

import school.sptech.CleanArchitecture.core.domain.entity.Lote;

public class LoteEntityMapper {

    public static Lote ofEntity(LoteEntity entity) {
        Lote domain = new Lote();
        domain.setIdLote(entity.getIdLote());
        domain.setDescricao(entity.getDescricao());
        domain.setDataEntrada(entity.getDataEntrada());
        domain.setParceiro(entity.getParceiro());
        domain.setResponsavel(entity.getResponsavel());
        return domain;
    }

    public static LoteEntity ofDomain(Lote domain) {
        LoteEntity entity = new LoteEntity();
        entity.setIdLote(domain.getIdLote());
        entity.setDescricao(domain.getDescricao());
        entity.setDataEntrada(domain.getDataEntrada());
        entity.setParceiro(domain.getParceiro());
        entity.setResponsavel(domain.getResponsavel());
        return entity;
    }
}
