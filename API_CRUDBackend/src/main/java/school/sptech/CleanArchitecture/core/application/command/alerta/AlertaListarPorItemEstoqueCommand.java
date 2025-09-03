package school.sptech.CleanArchitecture.core.application.command.alerta;

import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa.ConfeccaoRoupaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira.PrateleiraEntity;

import java.util.Set;

public class AlertaListarPorItemEstoqueCommand {
    private Integer idItemEstoque;
    private String descricao;
    private String complemento;
    private Double qtdMinimo;
    private Double qtdArmazenado;

    public Integer getIdItemEstoque() {
        return idItemEstoque;
    }

    public void setIdItemEstoque(Integer idItemEstoque) {
        this.idItemEstoque = idItemEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Double getQtdMinimo() {
        return qtdMinimo;
    }

    public void setQtdMinimo(Double qtdMinimo) {
        this.qtdMinimo = qtdMinimo;
    }

    public Double getQtdArmazenado() {
        return qtdArmazenado;
    }

    public void setQtdArmazenado(Double qtdArmazenado) {
        this.qtdArmazenado = qtdArmazenado;
    }
}
