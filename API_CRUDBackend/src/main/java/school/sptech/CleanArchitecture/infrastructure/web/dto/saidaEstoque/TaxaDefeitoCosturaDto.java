package school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque;

public class TaxaDefeitoCosturaDto {
    private String nomeCostureira;
    private Long qtdDefeito;
    private Long totalPeças;

    public TaxaDefeitoCosturaDto() {
    }

    public TaxaDefeitoCosturaDto(String nomeCostureira, Long qtdDefeito, Long totalPeças) {
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

    public Long getQtdDefeito() {
        return qtdDefeito;
    }

    public void setQtdDefeito(Long qtdDefeito) {
        this.qtdDefeito = qtdDefeito;
    }

    public Long getTotalPeças() {
        return totalPeças;
    }

    public void setTotalPeças(Long totalPeças) {
        this.totalPeças = totalPeças;
    }
}
