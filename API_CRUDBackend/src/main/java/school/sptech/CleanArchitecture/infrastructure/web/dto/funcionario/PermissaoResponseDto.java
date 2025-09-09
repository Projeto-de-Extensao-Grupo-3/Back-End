
package school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario;

public class PermissaoResponseDto {
    private String descricao;

    public PermissaoResponseDto() {}
    public PermissaoResponseDto(String descricao) { this.descricao = descricao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
