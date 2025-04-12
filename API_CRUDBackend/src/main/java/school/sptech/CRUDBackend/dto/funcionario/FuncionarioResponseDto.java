package school.sptech.CRUDBackend.dto.funcionario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResponseDto {
    private Integer idFuncionario;
    private String nome;
    private String telefone;
    private String email;
}
