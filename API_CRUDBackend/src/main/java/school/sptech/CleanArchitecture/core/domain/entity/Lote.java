package school.sptech.CleanArchitecture.core.domain.entity;

import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntity;

import java.time.LocalDateTime;

public class Lote {
    private Integer idLote;
    private String descricao;
    private LocalDateTime dataEntrada;
    private Parceiro parceiro;
    private Funcionario responsavel;

    public Lote() {
    }

    public Lote(LoteEntity lote) {
        this.idLote = lote.getIdLote();
        this.descricao = lote.getDescricao();
        this.dataEntrada = lote.getDataEntrada();
        this.parceiro = new Parceiro(lote.getParceiro().getIdParceiro());
        this.responsavel = new Funcionario(lote.getResponsavel());
    }

    public Lote(Integer id) {
        this.idLote = id;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }
}
