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

public class FuncionarioListarDto {
    @Schema(description = "Id do funcionário", example = "1")
    private Integer idFuncionario;

    @Schema(description = "Nome do funcionário", example = "Fernando")
    private String nome;

    @Schema(description = "Email do funcionáorio", example = "fernando@.com")
    private String email;
}
