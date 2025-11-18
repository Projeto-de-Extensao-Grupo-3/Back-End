package school.sptech.CleanArchitecture.core.application.command.funcionario;

import jakarta.validation.constraints.NotBlank;
import school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario.PermissaoRequestDto;

import java.util.Set;

public class FuncionarioAtualizarRequestDto {
    @NotBlank
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Set<PermissaoRequestDto> permissoes;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Set<PermissaoRequestDto> getPermissoes() { return permissoes; }
    public void setPermissoes(Set<PermissaoRequestDto> permissoes) { this.permissoes = permissoes; }
}
