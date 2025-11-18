package school.sptech.CleanArchitecture.core.domain.entity;


import jakarta.validation.constraints.NotNull;

public class ConfeccaoRoupa {

    private Integer idConfeccaoRoupa;
    private ItemEstoque roupa;
    private ItemEstoque tecido;
    private Double qtdTecido;

    public ConfeccaoRoupa() {
    }

    public ConfeccaoRoupa(Integer idConfeccaoRoupa, ItemEstoque roupa, ItemEstoque tecido, Double qtdTecido) {
        this.idConfeccaoRoupa = idConfeccaoRoupa;
        this.roupa = roupa;
        this.tecido = tecido;
        this.qtdTecido = qtdTecido;
    }

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

    @Override
    public String toString() {
        return "ConfeccaoRoupa{" +
                "idConfeccaoRoupa=" + idConfeccaoRoupa +
                ", roupa=" + roupa +
                ", tecido=" + tecido +
                ", qtdTecido=" + qtdTecido +
                '}';
    }
}
