
package school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario;

import java.util.Set;

public class FuncionarioResponseDto {
    private Integer idFuncionario;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Set<PermissaoResponseDto> permissoes;

    public FuncionarioResponseDto() {}

    public FuncionarioResponseDto(Integer idFuncionario, String nome, String cpf, String telefone, String email, Set<PermissaoResponseDto> permissoes) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.permissoes = permissoes;
    }

    public Integer getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(Integer idFuncionario) { this.idFuncionario = idFuncionario; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Set<PermissaoResponseDto> getPermissoes() { return permissoes; }
    public void setPermissoes(Set<PermissaoResponseDto> permissoes) { this.permissoes = permissoes; }
}
