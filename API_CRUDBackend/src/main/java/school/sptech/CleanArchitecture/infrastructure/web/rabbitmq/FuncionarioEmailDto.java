package school.sptech.CleanArchitecture.infrastructure.web.rabbitmq;

import lombok.*;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

@Data
public class FuncionarioEmailDto {
    private String nome;
    private String telefone;
    private String email;

    public FuncionarioEmailDto(Funcionario responsavel) {
        this.nome = responsavel.getNome();
        this.telefone = responsavel.getTelefone().getValue();
        this.email = responsavel.getEmail().getValue();
    }
}
