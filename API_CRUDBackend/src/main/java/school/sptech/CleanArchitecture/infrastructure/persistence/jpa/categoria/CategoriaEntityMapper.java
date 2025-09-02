package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria;

import school.sptech.CleanArchitecture.core.domain.entity.Categoria;

import java.util.Objects;

public class CategoriaEntityMapper {

    public static CategoriaEntity ofDomain (Categoria domain){
        if (Objects.isNull(domain)) {
            return null;
        }
        CategoriaEntity categoriaPai = null;
        if (domain.getCategoriaPai() != null) {
            categoriaPai = new CategoriaEntity();
            categoriaPai.setIdCategoria(domain.getCategoriaPai().getIdCategoria());
        }
        var entity = new CategoriaEntity();
        entity.setIdCategoria(domain.getIdCategoria());
        entity.setNome(domain.getNome());
        entity.setCategoriaPai(categoriaPai);

        return entity;
    }

    public static Categoria ofEntity(CategoriaEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        Categoria categoriaPai = null;
        if (entity.getCategoriaPai() != null) {
            categoriaPai = new Categoria();
            categoriaPai.setIdCategoria(entity.getCategoriaPai().getIdCategoria());
        }
        var domain = new Categoria();
        domain.setIdCategoria(entity.getIdCategoria());
        domain.setNome(entity.getNome());
        domain.setCategoriaPai(categoriaPai);

        return domain;
    }
}
