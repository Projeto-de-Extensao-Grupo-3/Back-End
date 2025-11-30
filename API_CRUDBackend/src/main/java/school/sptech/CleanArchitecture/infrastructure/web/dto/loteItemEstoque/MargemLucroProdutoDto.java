package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

public class MargemLucroProdutoDto {
    private Integer idProduto;
    private String nomeProduto;
    private Double margemLucro;

    public MargemLucroProdutoDto() {
    }

    public MargemLucroProdutoDto(Integer idProduto, String nomeProduto, Double margemLucro) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.margemLucro = margemLucro;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }
}
