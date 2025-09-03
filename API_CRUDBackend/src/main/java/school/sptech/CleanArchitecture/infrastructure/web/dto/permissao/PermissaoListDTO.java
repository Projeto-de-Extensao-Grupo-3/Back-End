package school.sptech.CleanArchitecture.infrastructure.web.dto.permissao;

public class PermissaoListDTO {

    private Integer idDescricao;
    private String descricao;

    public PermissaoListDTO(Integer idDescricao, String descricao) {
        this.idDescricao = idDescricao;
        this.descricao = descricao;
    }

    public PermissaoListDTO() {
    }

    public Integer getIdDescricao() {
        return idDescricao;
    }

    public void setIdDescricao(Integer idDescricao) {
        this.idDescricao = idDescricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
