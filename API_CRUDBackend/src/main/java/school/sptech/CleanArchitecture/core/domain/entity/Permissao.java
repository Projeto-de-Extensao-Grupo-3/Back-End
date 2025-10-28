package school.sptech.CleanArchitecture.core.domain.entity;

public class Permissao {
    private Integer idPermissao;
    private String descricao;

    public Permissao() {}

    public Permissao(Integer idPermissao) {
        this.idPermissao = idPermissao;
    }

    public Permissao(Integer idPermissao, String descricao) {
        this.idPermissao = idPermissao;
        this.descricao = descricao;
    }

    public Integer getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Integer idPermissao) {
        this.idPermissao = idPermissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
