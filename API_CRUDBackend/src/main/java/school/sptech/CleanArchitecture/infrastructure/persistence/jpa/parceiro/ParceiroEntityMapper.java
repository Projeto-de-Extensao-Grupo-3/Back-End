package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro;

import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;

public class ParceiroEntityMapper {

    public static Parceiro ofEntity(ParceiroEntity entity) {
        Parceiro domain = new Parceiro();
        domain.setId(entity.getIdParceiro());
        domain.setCategoria(entity.getCategoria());
        domain.setNome(entity.getNome());
        domain.setTelefone(entity.getTelefone());
        new EmailVo(entity.getEmail());
        domain.setIdentificacao(entity.getIdentificacao());
        return domain;
    }

    public static ParceiroEntity ofDomain(Parceiro domain) {
        ParceiroEntity entity = new ParceiroEntity();
        entity.setIdParceiro(domain.getId());
        entity.setCategoria(domain.getCategoria());
        entity.setNome(domain.getNome());
        entity.setTelefone(domain.getTelefone());
        entity.setEmail(domain.getEmail().getValue());
        entity.setIdentificacao(domain.getIdentificacao());
        return entity;
    }
}
