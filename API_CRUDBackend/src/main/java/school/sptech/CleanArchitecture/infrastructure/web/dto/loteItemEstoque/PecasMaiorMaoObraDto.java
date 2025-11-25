package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

public class PecasMaiorMaoObraDto {
    private Integer idProduto;
    private Double custoCostureira;
    private Double custoTecidos;
    private Double custoTotal;
    private Double preco;
    private Double margemLucro;
    private String descricao;

    public PecasMaiorMaoObraDto() {
    }

    public PecasMaiorMaoObraDto(Integer idProduto, Double custoCostureira, Double custoTecidos, Double custoTotal, Double preco, Double margemLucro, String descricao) {
        this.idProduto = idProduto;
        this.custoCostureira = custoCostureira;
        this.custoTecidos = custoTecidos;
        this.custoTotal = custoTotal;
        this.preco = preco;
        this.margemLucro = margemLucro;
        this.descricao = descricao;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Double getCustoCostureira() {
        return custoCostureira;
    }

    public void setCustoCostureira(Double custoCostureira) {
        this.custoCostureira = custoCostureira;
    }

    public Double getCustoTecidos() {
        return custoTecidos;
    }

    public void setCustoTecidos(Double custoTecidos) {
        this.custoTecidos = custoTecidos;
    }

    public Double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(Double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
