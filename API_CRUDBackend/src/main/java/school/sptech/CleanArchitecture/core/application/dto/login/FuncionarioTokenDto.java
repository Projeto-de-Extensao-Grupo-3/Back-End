package school.sptech.CleanArchitecture.core.application.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FuncionarioTokenDto {
    private String nome;
    private String email;
    private String token;

}
