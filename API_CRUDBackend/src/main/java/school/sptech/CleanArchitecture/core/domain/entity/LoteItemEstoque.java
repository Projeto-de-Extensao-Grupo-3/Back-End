package school.sptech.CleanArchitecture.core.domain.entity;

import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntity;

public class LoteItemEstoque {
    private Integer idLoteItemEstoque;
    private Double qtdItem;
    private Double preco;
    private ItemEstoque itemEstoque;
    private Lote lote;

    public LoteItemEstoque() {
    }

    public LoteItemEstoque(Integer idLoteItemEstoque, Double qtdItem, Double preco, ItemEstoque itemEstoque, Lote lote) {
        this.idLoteItemEstoque = idLoteItemEstoque;
        this.qtdItem = qtdItem;
        this.preco = preco;
        this.itemEstoque = itemEstoque;
        this.lote = lote;
    }

    public LoteItemEstoque(Double qtdItem, Double preco, ItemEstoque itemEstoque, Lote lote) {
        this.qtdItem = qtdItem;
        this.preco = preco;
        this.itemEstoque = itemEstoque;
        this.lote = lote;
    }

    public LoteItemEstoque(LoteItemEstoqueEntity loteItemEstoque) {
        this.idLoteItemEstoque = loteItemEstoque.getIdLoteItemEstoque();
    }


    public Integer getIdLoteItemEstoque() {
        return idLoteItemEstoque;
    }

    public void setIdLoteItemEstoque(Integer idLoteItemEstoque) {
        this.idLoteItemEstoque = idLoteItemEstoque;
    }

    public Double getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(Double qtdItem) {
        this.qtdItem = qtdItem;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public ItemEstoque getItemEstoque() {
        return itemEstoque;
    }

    public void setItemEstoque(ItemEstoque itemEstoque) {
        this.itemEstoque = itemEstoque;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }
}
