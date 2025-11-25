package school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque;

public class EvolucaoVendasDto {
    private String mes_atual;
    private Integer totalVendasAtual;
    private Double crescimentoPercentual;

    public EvolucaoVendasDto(String mes_atual, Double totalVendasAtual, Double crescimentoPercentual) {
        this.mes_atual = mes_atual;
        this.totalVendasAtual = totalVendasAtual.intValue();
        this.crescimentoPercentual = crescimentoPercentual;
    }

    public String getMes_atual() {
        return mes_atual;
    }

    public void setMes_atual(String mes_atual) {
        this.mes_atual = mes_atual;
    }

    public Integer getTotalVendasAtual() {
        return totalVendasAtual;
    }

    public void setTotalVendasAtual(Integer totalVendasAtual) {
        this.totalVendasAtual = totalVendasAtual;
    }

    public Double getCrescimentoPercentual() {
        return crescimentoPercentual;
    }

    public void setCrescimentoPercentual(Double crescimentoPercentual) {
        this.crescimentoPercentual = crescimentoPercentual;
    }
}
