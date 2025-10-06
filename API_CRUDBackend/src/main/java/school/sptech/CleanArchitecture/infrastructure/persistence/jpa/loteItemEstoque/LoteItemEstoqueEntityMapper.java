package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque;

import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntityMapper;

public class LoteItemEstoqueEntityMapper {
    public static LoteItemEstoque ofEntity(LoteItemEstoqueEntity entity) {
        LoteItemEstoque domain = new LoteItemEstoque();
        domain.setIdLoteItemEstoque(entity.getIdLoteItemEstoque());
        domain.setQtdItem(entity.getQtdItem());
        domain.setPreco(entity.getPreco());
        domain.setItemEstoque(new ItemEstoque(entity.getItemEstoque()));
        domain.setLote(new Lote(entity.getLote()));
        return domain;
    }

    public static LoteItemEstoqueEntity ofDomain(LoteItemEstoque domain) {
        ItemEstoque itemEstoque = new ItemEstoque();
        itemEstoque.setIdItemEstoque(domain.getItemEstoque().getIdItemEstoque());
        Lote lote = new Lote();
        lote.setIdLote(domain.getLote().getIdLote());

        LoteItemEstoqueEntity entity = new LoteItemEstoqueEntity();
        entity.setIdLoteItemEstoque(domain.getIdLoteItemEstoque());
        entity.setQtdItem(domain.getQtdItem());
        entity.setPreco(domain.getPreco());
        entity.setItemEstoque(new ItemEstoqueEntity(domain.getItemEstoque()));
        entity.setLote(new LoteEntity(domain.getLote().getIdLote()));
        return entity;
    }
}
