package school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque;

import java.math.BigDecimal;

public class DefeitosPorRoupaDto {
    private String roupa;
    private Integer qtdDefeitos;
    private Integer qtdTotalSaidas;
    private Double taxaDefeitoPercentual;

    public DefeitosPorRoupaDto(String roupa, Long qtdDefeitos, Long qtdTotalSaidas, BigDecimal taxaDefeitoPercentual) {
        this.roupa = roupa;
        this.qtdDefeitos = qtdDefeitos.intValue();
        this.qtdTotalSaidas = qtdTotalSaidas.intValue();
        this.taxaDefeitoPercentual = taxaDefeitoPercentual.doubleValue();
    }

    public String getRoupa() {
        return roupa;
    }

    public void setRoupa(String roupa) {
        this.roupa = roupa;
    }

    public Integer getQtdDefeitos() {
        return qtdDefeitos;
    }

    public void setQtdDefeitos(Integer qtdDefeitos) {
        this.qtdDefeitos = qtdDefeitos;
    }

    public Integer getQtdTotalSaidas() {
        return qtdTotalSaidas;
    }

    public void setQtdTotalSaidas(Integer qtdTotalSaidas) {
        this.qtdTotalSaidas = qtdTotalSaidas;
    }

    public Double getTaxaDefeitoPercentual() {
        return taxaDefeitoPercentual;
    }

    public void setTaxaDefeitoPercentual(Double taxaDefeitoPercentual) {
        this.taxaDefeitoPercentual = taxaDefeitoPercentual;
    }
}
