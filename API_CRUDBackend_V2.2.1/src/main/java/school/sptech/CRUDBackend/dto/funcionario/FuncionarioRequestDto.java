package school.sptech.CRUDBackend.dto.funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioRequestDto {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
}
