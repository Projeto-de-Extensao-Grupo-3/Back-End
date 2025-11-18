
package school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario;

public class PermissaoResponseDto {
    private Integer idPermissao;
    private String descricao;

    public PermissaoResponseDto() {}

    public PermissaoResponseDto(Integer idPermissao, String descricao) {
        this.idPermissao = idPermissao;
        this.descricao = descricao;
    }

    public Integer getIdPermissao() { return idPermissao; }
    public void setIdPermissao(Integer idPermissao) { this.idPermissao = idPermissao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
