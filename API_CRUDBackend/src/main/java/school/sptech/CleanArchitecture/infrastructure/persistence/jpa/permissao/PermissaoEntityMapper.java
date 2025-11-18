package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.permissao;

import school.sptech.CleanArchitecture.core.domain.entity.Permissao;

public class PermissaoEntityMapper {

    public static Permissao ofEntity(PermissaoEntity entity) {
        Permissao domain = new Permissao();
        domain.setIdPermissao(entity.getIdPermissao());
        domain.setDescricao(entity.getDescricao());
        return domain;
    }

    public static PermissaoEntity ofDomain(Permissao domain) {
        PermissaoEntity entity = new PermissaoEntity();
        entity.setIdPermissao(domain.getIdPermissao());
        entity.setDescricao(domain.getDescricao());
        return entity;
    }
}
