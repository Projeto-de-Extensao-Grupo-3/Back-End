package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa;

import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;

import java.util.Objects;

public class ConfeccaoRoupaEntityMapper {

    public static ConfeccaoRoupaEntity ofDomain (ConfeccaoRoupa domain){
        if (Objects.isNull(domain)) {
            return null;
        }

        var entity = new ConfeccaoRoupaEntity();
        
        ItemEstoqueEntity roupaEntity = new ItemEstoqueEntity();
        roupaEntity.setIdItemEstoque(domain.getRoupa().getIdItemEstoque());
        
        ItemEstoqueEntity tecidoEntity = new ItemEstoqueEntity();
        tecidoEntity.setIdItemEstoque(domain.getTecido().getIdItemEstoque());

        entity.setIdConfeccaoRoupa(domain.getIdConfeccaoRoupa());
        entity.setRoupa(roupaEntity);
        entity.setTecido(tecidoEntity);
        entity.setQtdTecido(domain.getQtdTecido());

        return entity;
    }

    public static ConfeccaoRoupa ofEntity(ConfeccaoRoupaEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        var domain = new ConfeccaoRoupa();
        
        ItemEstoque roupaDomain = new ItemEstoque();
        roupaDomain.setIdItemEstoque(entity.getRoupa().getIdItemEstoque());

        ItemEstoque tecidoDomain = new ItemEstoque();
        tecidoDomain.setIdItemEstoque(entity.getTecido().getIdItemEstoque());

        domain.setIdConfeccaoRoupa(entity.getIdConfeccaoRoupa());
        domain.setRoupa(roupaDomain);
        domain.setTecido(tecidoDomain);
        domain.setQtdTecido(entity.getQtdTecido());

        return domain;
    }

}
