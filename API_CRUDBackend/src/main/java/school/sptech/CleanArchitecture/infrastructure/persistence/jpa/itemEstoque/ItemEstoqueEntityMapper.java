package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque;

import school.sptech.CleanArchitecture.core.domain.entity.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa.ConfeccaoRoupaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira.PrateleiraEntity;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemEstoqueEntityMapper {

    public static ItemEstoqueEntity ofDomain (ItemEstoque domain){
        if (Objects.isNull(domain)) {
            return null;
        }

        var entity = new ItemEstoqueEntity();
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setIdCategoria(domain.getCategoria().getIdCategoria());

        Set<Categoria> caracteristicas = domain.getCaracteristicas();
        Set<CategoriaEntity> caracteristicasEntity =
                caracteristicas.stream()
                                .map(c -> {
                                    CategoriaEntity categoriaEntity = new CategoriaEntity();
                                    categoriaEntity.setIdCategoria(c.getIdCategoria());
                                    return categoriaEntity;
                                        }
                                ).collect(Collectors.toSet());

        PrateleiraEntity prateleiraEntity = new PrateleiraEntity();
        prateleiraEntity.setIdPrateleira(domain.getPrateleira().getIdPrateleira());

        Set<ConfeccaoRoupa> confeccaoRoupa = domain.getConfeccaoRoupa();
        Set<ConfeccaoRoupaEntity> confeccaoRoupaEntities = confeccaoRoupa.stream()
                .map(cr -> {
                    ConfeccaoRoupaEntity confeccaoEntity = new ConfeccaoRoupaEntity();
                    confeccaoEntity.setIdConfeccaoRoupa(cr.getIdConfeccaoRoupa()); // só seta o id
                    return confeccaoEntity;
                })
                .collect(Collectors.toSet());

        ImagemEntity imagemEntity = new ImagemEntity();
        imagemEntity.setIdImagem(domain.getImagem().getIdImagem());

        entity.setIdItemEstoque(domain.getIdItemEstoque());
        entity.setDescricao(domain.getDescricao());
        entity.setComplemento(domain.getComplemento());
        entity.setPeso(domain.getPeso());
        entity.setQtdMinimo(domain.getQtdMinimo());
        entity.setQtdArmazenado(domain.getQtdArmazenado());
        entity.setCategoria(categoria);
        entity.setCaracteristicas(caracteristicasEntity);
        entity.setPrateleira(prateleiraEntity);
        entity.setConfeccaoRoupa(confeccaoRoupaEntities);
        entity.setPreco(domain.getPreco());
        entity.setImagem(imagemEntity);

        return entity;
    }

    public static ItemEstoque ofEntity(ItemEstoqueEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        var domain = new ItemEstoque();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(entity.getCategoria().getIdCategoria());

        Set<CategoriaEntity> caracteristicas = entity.getCaracteristicas();
        Set<Categoria> caracteristicasEntity =
                caracteristicas.stream()
                        .map(c -> {
                            Categoria categoriaDomain = new Categoria();
                            categoriaDomain.setIdCategoria(c.getIdCategoria());
                                    return categoriaDomain;
                                }
                        ).collect(Collectors.toSet());

        Prateleira prateleira = new Prateleira();
        prateleira.setIdPrateleira(entity.getPrateleira().getIdPrateleira());

        Set<ConfeccaoRoupaEntity> confeccaoRoupaEntity = entity.getConfeccaoRoupa();
        Set<ConfeccaoRoupa> confeccaoRoupas = confeccaoRoupaEntity.stream()
                .map(cr -> {
                    ConfeccaoRoupa confeccao = new ConfeccaoRoupa();
                    confeccao.setIdConfeccaoRoupa(cr.getIdConfeccaoRoupa()); // só seta o id
                    return confeccao;
                })
                .collect(Collectors.toSet());

        Imagem imagem = new Imagem();
        imagem.setIdImagem(entity.getImagem().getIdImagem());

        domain.setIdItemEstoque(entity.getIdItemEstoque());
        domain.setDescricao(entity.getDescricao());
        domain.setComplemento(entity.getComplemento());
        domain.setPeso(entity.getPeso());
        domain.setQtdMinimo(entity.getQtdMinimo());
        domain.setQtdArmazenado(entity.getQtdArmazenado());
        domain.setCategoria(categoria);
        domain.setCaracteristicas(caracteristicasEntity);
        domain.setPrateleira(prateleira);
        domain.setConfeccaoRoupa(confeccaoRoupas);
        domain.setPreco(domain.getPreco());
        domain.setImagem(imagem);

        return domain;
    }
}
