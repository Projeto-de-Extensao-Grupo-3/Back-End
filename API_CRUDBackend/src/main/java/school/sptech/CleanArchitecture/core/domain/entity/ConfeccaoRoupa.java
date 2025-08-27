package school.sptech.CleanArchitecture.core.domain.entity;

import school.sptech.CRUDBackend.entity.ItemEstoque;

public class ConfeccaoRoupa {

    private Integer idConfeccaoRoupa;
    private school.sptech.CRUDBackend.entity.ItemEstoque roupa;
    private ItemEstoque tecido;
    private Double qtdTecido;

    public Integer getIdConfeccaoRoupa() {
        return idConfeccaoRoupa;
    }

    public void setIdConfeccaoRoupa(Integer idConfeccaoRoupa) {
        this.idConfeccaoRoupa = idConfeccaoRoupa;
    }

    public ItemEstoque getRoupa() {
        return roupa;
    }

    public void setRoupa(ItemEstoque roupa) {
        this.roupa = roupa;
    }

    public ItemEstoque getTecido() {
        return tecido;
    }

    public void setTecido(ItemEstoque tecido) {
        this.tecido = tecido;
    }

    public Double getQtdTecido() {
        return qtdTecido;
    }

    public void setQtdTecido(Double qtdTecido) {
        this.qtdTecido = qtdTecido;
    }
}
