package school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque;

public class EvolucaoVendasDto {
    private String periodo;
    private Double faturamento_bruto;
    private Double lucro;
    private Double custos;

    public EvolucaoVendasDto(String periodo, Double crescimentoPercentual, Double lucro, Double custos) {
        this.periodo = periodo;
        this.faturamento_bruto = crescimentoPercentual;
        this.lucro = lucro;
        this.custos = custos;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Double getFaturamento_bruto() {
        return faturamento_bruto;
    }

    public void setFaturamento_bruto(Double faturamento_bruto) {
        this.faturamento_bruto = faturamento_bruto;
    }

    public Double getLucro() {
        return lucro;
    }

    public void setLucro(Double lucro) {
        this.lucro = lucro;
    }

    public Double getCustos() {
        return custos;
    }

    public void setCustos(Double custos) {
        this.custos = custos;
    }
}
