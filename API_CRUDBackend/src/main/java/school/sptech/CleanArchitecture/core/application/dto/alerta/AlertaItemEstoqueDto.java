package school.sptech.CleanArchitecture.core.application.dto.alerta;

public class AlertaItemEstoqueDto {
    private Integer idItemEstoque;
    private String descricao;
    private String complemento;
    private Double qtdMinimo;
    private Double qtdArmazenado;

    public AlertaItemEstoqueDto() {
    }

    public AlertaItemEstoqueDto(Integer idItemEstoque, String descricao, String complemento, Double qtdMinimo, Double qtdArmazenado) {
        this.idItemEstoque = idItemEstoque;
        this.descricao = descricao;
        this.complemento = complemento;
        this.qtdMinimo = qtdMinimo;
        this.qtdArmazenado = qtdArmazenado;
    }

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
