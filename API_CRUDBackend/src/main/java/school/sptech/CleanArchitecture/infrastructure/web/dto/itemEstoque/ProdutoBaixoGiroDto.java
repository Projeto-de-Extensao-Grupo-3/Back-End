package school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque;

import java.math.BigDecimal;

public class ProdutoBaixoGiroDto {

    private String produto;
    private Double totalVendido;
    private Long quantidadeVendas;
    private Double estoqueAtual;
    private Long diasSemVender;
    private String statusRecomendacao;

    public ProdutoBaixoGiroDto() {
    }

    public ProdutoBaixoGiroDto(String produto, Double totalVendido, Long quantidadeVendas, Double estoqueAtual, Long diasSemVender, String statusRecomendacao) {
        this.produto = produto;
        this.totalVendido = totalVendido;
        this.quantidadeVendas = quantidadeVendas;
        this.estoqueAtual = estoqueAtual;
        this.diasSemVender = diasSemVender;
        this.statusRecomendacao = statusRecomendacao;
    }

//    public ProdutoBaixoGiroDto(String produto, BigDecimal totalVendido, Long quantidadeVendas, BigDecimal estoqueAtual, Long diasSemVender, String statusRecomendacao) {
//        this.produto = produto;
//        this.totalVendido = totalVendido.doubleValue();
//        this.quantidadeVendas = quantidadeVendas;
//        this.estoqueAtual = estoqueAtual.doubleValue();
//        this.diasSemVender = diasSemVender;
//        this.statusRecomendacao = statusRecomendacao;
//    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public Long getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public void setQuantidadeVendas(Long quantidadeVendas) {
        this.quantidadeVendas = quantidadeVendas;
    }

    public Double getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Double estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public Long getDiasSemVender() {
        return diasSemVender;
    }

    public void setDiasSemVender(Long diasSemVender) {
        this.diasSemVender = diasSemVender;
    }

    public String getStatusRecomendacao() {
        return statusRecomendacao;
    }

    public void setStatusRecomendacao(String statusRecomendacao) {
        this.statusRecomendacao = statusRecomendacao;
    }
}