package school.sptech.CleanArchitecture.infrastructure.web.dto.loginDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioCriacaoDto {

    @Size(min = 3, max = 80)
    @Schema(description = "Nome do funcionário", example = "Fernando", required = true)
    private String nome;

    @Email
    @Schema(description = "Email do funcionário", example = "fernando_amorim@gmail.com")
    private String email;

    @Size(min = 6, max = 45)
    @Schema(description = "Senha do funcionário", example = "123456")
    private String senha;

}
