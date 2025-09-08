package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.corteTecido;

import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;

public class CorteTecidoEntityMapper {

    public static CorteTecido ofEntity(CorteTecidoEntity entity) {
        CorteTecido domain = new CorteTecido();
        domain.setIdCorteTecido(entity.getIdCorteTecido());
        domain.setInicio(entity.getInicio());
        domain.setTermino(entity.getTermino());
        domain.setFuncionario(entity.getFuncionario());
        domain.setLoteItemEstoque(entity.getLoteItemEstoque());
        return domain;
    }

    public static CorteTecidoEntity ofDomain(CorteTecido domain) {
        CorteTecidoEntity entity = new CorteTecidoEntity();
        entity.setIdCorteTecido(domain.getIdCorteTecido());
        entity.setInicio(domain.getInicio());
        entity.setTermino(domain.getTermino());
        entity.setFuncionario(domain.getFuncionario());
        entity.setLoteItemEstoque(domain.getLoteItemEstoque());
        return entity;
    }
}
