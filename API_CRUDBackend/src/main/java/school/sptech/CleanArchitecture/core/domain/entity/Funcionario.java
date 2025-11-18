
package school.sptech.CleanArchitecture.core.domain.entity;

import school.sptech.CleanArchitecture.core.domain.valueObject.CpfVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.TelefoneVo;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;

import java.util.Set;

public class Funcionario {

    private Integer idFuncionario;
    private String nome;
    private CpfVo cpf;
    private TelefoneVo telefone;
    private EmailVo email;
    private String senha;
    private Set<Permissao> permissoes;

    public Funcionario() { }

    public Funcionario(Integer idFuncionario, String nome, CpfVo cpf, TelefoneVo telefone, EmailVo email, Set<Permissao> permissoes) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.permissoes = permissoes;
    }

    public Funcionario(Integer idFuncionario, String nome, CpfVo cpf, TelefoneVo telefone, EmailVo email, String senha, Set<Permissao> permissoes) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.permissoes = permissoes;
    }

    public Funcionario(FuncionarioEntity funcionario) {
        this.idFuncionario = funcionario.getIdFuncionario();
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CpfVo getCpf() {
        return cpf;
    }

    public void setCpf(CpfVo cpf) {
        this.cpf = cpf;
    }

    public TelefoneVo getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneVo telefone) {
        this.telefone = telefone;
    }

    public EmailVo getEmail() {
        return email;
    }

    public void setEmail(EmailVo email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncionario=" + idFuncionario +
                ", nome='" + nome + '\'' +
                ", cpf=" + cpf +
                ", telefone=" + telefone +
                ", email=" + email +
                ", senha='" + senha + '\'' +
                ", permissoes=" + permissoes +
                '}';
    }
}
