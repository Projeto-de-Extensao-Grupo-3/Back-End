package school.sptech.CleanArchitecture.core.domain.entity;

public class Permissao {

    private Integer idPermissao;
    private String descricao;

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
