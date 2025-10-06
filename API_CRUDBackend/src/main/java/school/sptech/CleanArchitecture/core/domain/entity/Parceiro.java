package school.sptech.CleanArchitecture.core.domain.entity;


import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;

public class Parceiro {
    private Integer id;
    private String categoria;
    private String nome;
    private String telefone;
    private EmailVo email;
    private String endereco;
    private String identificacao;

    public Parceiro() {
    }

    public Parceiro(String categoria, String nome, String telefone, EmailVo email, String endereco, String identificacao) {
        this.categoria = categoria;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.identificacao = identificacao;
    }

    public Parceiro(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EmailVo getEmail() {
        return email;
    }

    public void setEmail(EmailVo email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
}
