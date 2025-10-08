package school.sptech.CleanArchitecture.infrastructure.web.dto.permissao;

public class PermissaoListDTO {

    private Integer idPermissao;
    private String descricao;

    public PermissaoListDTO(Integer idPermissao, String descricao) {
        this.idPermissao = idPermissao;
        this.descricao = descricao;
    }

    public PermissaoListDTO() {
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
