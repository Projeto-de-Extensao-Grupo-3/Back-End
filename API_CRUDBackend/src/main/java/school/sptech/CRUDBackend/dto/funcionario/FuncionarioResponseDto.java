package school.sptech.CRUDBackend.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Schema(description = "DTO para resposta de Funcionário.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResponseDto {
    private Integer idFuncionario;
    @Schema(description = "Nome do Funcionário", example = "Fernando")
    private String nome;
    @Schema(description = "CPF do Funcionário", example = "XXX-XXX-XXX/XX")
    private String telefone;
    @Schema(description = "Email do Funcionário", example = "fernando@gmail.com")
    private String email;
    private Set<FuncionarioPermissaoResponseDto> permissoes;
}
