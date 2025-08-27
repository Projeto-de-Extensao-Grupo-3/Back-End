package school.sptech.CleanArchitecture.core.domain.entity;

import java.util.Set;

public class ItemEstoque {

    private Integer idItemEstoque;
    private String descricao;
    private String complemento;
    private Double peso;
    private Double qtdMinimo;
    private Double qtdArmazenado;
    private Set<Categoria> caracteristicas;
    private Prateleira prateleira;
    private Set<ConfeccaoRoupa> confeccaoRoupa;
    private Double preco;
    private Imagem imagem;

    public ItemEstoque() {
    }

    public ItemEstoque(Integer idItemEstoque, String descricao, String complemento, Double peso, Double qtdMinimo, Double qtdArmazenado, Set<Categoria> caracteristicas, Prateleira prateleira, Set<ConfeccaoRoupa> confeccaoRoupa, Double preco, Imagem imagem) {
        this.idItemEstoque = idItemEstoque;
        this.descricao = descricao;
        this.complemento = complemento;
        this.peso = peso;
        this.qtdMinimo = qtdMinimo;
        this.qtdArmazenado = qtdArmazenado;
        this.caracteristicas = caracteristicas;
        this.prateleira = prateleira;
        this.confeccaoRoupa = confeccaoRoupa;
        this.preco = preco;
        this.imagem = imagem;
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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
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

    public Set<Categoria> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<Categoria> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Prateleira getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(Prateleira prateleira) {
        this.prateleira = prateleira;
    }

    public Set<ConfeccaoRoupa> getConfeccaoRoupa() {
        return confeccaoRoupa;
    }

    public void setConfeccaoRoupa(Set<ConfeccaoRoupa> confeccaoRoupa) {
        this.confeccaoRoupa = confeccaoRoupa;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }
}
