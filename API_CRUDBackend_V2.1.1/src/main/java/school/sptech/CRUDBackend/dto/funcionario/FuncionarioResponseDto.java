package school.sptech.CRUDBackend.dto.funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioResponseDto {
    private Integer idFuncionario;
    private String nome;
    private String telefone;
    private String email;
}
