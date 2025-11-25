package school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque;

public class TaxaDefeitoCosturaDto {
    private String nomeCostureira;
    private Integer qtdDefeito;
    private Integer totalPeças;

    public TaxaDefeitoCosturaDto() {
    }

    public TaxaDefeitoCosturaDto(String nomeCostureira, Integer qtdDefeito, Integer totalPeças) {
        this.nomeCostureira = nomeCostureira;
        this.qtdDefeito = qtdDefeito;
        this.totalPeças = totalPeças;
    }

    public String getNomeCostureira() {
        return nomeCostureira;
    }

    public void setNomeCostureira(String nomeCostureira) {
        this.nomeCostureira = nomeCostureira;
    }

    public Integer getQtdDefeito() {
        return qtdDefeito;
    }

    public void setQtdDefeito(Integer qtdDefeito) {
        this.qtdDefeito = qtdDefeito;
    }

    public Integer getTotalPeças() {
        return totalPeças;
    }

    public void setTotalPeças(Integer totalPeças) {
        this.totalPeças = totalPeças;
    }
}
