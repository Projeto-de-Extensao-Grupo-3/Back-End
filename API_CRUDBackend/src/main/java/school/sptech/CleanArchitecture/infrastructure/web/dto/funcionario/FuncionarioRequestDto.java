
package school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Schema(description = "DTO para requisição de cadastro de um novo funcionário.")
public class FuncionarioRequestDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String telefone;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String senha;

    @NotNull
    private Set<PermissaoRequestDto> permissoes;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Set<PermissaoRequestDto> getPermissoes() { return permissoes; }
    public void setPermissoes(Set<PermissaoRequestDto> permissoes) { this.permissoes = permissoes; }
}
