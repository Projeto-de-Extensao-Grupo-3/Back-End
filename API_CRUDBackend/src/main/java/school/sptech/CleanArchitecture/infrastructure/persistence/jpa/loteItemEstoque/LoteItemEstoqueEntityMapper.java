package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque;

import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntityMapper;

public class LoteItemEstoqueEntityMapper {
    public static LoteItemEstoque ofEntity(LoteItemEstoqueEntity entity) {
        LoteItemEstoque domain = new LoteItemEstoque();
        domain.setIdLoteItemEstoque(entity.getIdLoteItemEstoque());
        domain.setQtdItem(entity.getQtdItem());
        domain.setPreco(entity.getPreco());
        if (entity.getItemEstoque() != null) {
            ItemEstoque itemEstoque = new ItemEstoque();
            itemEstoque.setIdItemEstoque(entity.getItemEstoque().getIdItemEstoque());
            domain.setItemEstoque(itemEstoque);
        }
        if (entity.getLote() != null) {
            Lote lote = new Lote();
            lote.setIdLote(entity.getLote().getIdLote());
            domain.setLote(lote);
        }
        return domain;
    }

    public static LoteItemEstoqueEntity ofDomain(LoteItemEstoque domain) {
        LoteItemEstoqueEntity entity = new LoteItemEstoqueEntity();
        entity.setIdLoteItemEstoque(domain.getIdLoteItemEstoque());
        entity.setQtdItem(domain.getQtdItem());
        entity.setPreco(domain.getPreco());
        entity.setItemEstoque(ItemEstoqueEntityMapper.ofDomain(domain.getItemEstoque()));
        entity.setLote(LoteEntityMapper.ofDomain(domain.getLote()));
        return entity;
    }
}
