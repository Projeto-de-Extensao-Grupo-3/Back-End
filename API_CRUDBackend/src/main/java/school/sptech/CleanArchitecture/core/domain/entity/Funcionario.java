package school.sptech.CleanArchitecture.core.domain.entity;

import school.sptech.CleanArchitecture.core.domain.valueObject.CpfVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.TelefoneVo;

import java.util.Set;

public class Funcionario {

    private Integer idFuncionario;
    private String nome;
    private CpfVo cpf;
    private TelefoneVo telefone;
    private EmailVo email;
    private String senha;
    private Set<Permissao> permissoes;

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
}
