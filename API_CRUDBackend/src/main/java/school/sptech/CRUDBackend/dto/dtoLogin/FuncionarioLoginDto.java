package school.sptech.CRUDBackend.dto.dtoLogin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FuncionarioLoginDto {
    @Schema(description = "Email do funcionário", example = "fernando_amorim@gmail.com")
    private String email;

    @Schema(description = "Senha do funcionário", example = "123456")
    private String senha;
}
