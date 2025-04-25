package school.sptech.CRUDBackend.dto.dtoLogin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FuncionarioTokenDto {
    private Integer idFuncionario;
    private String nome;
    private String email;
    private String token;

}
