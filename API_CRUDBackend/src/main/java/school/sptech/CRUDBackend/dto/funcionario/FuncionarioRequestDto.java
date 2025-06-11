package school.sptech.CRUDBackend.dto.funcionario;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import school.sptech.CRUDBackend.entity.Permissao;

import java.util.Set;

@Schema(description = "DTO para requisição de cadastro de um novo funcionário.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequestDto {
    @Schema(description = "Nome do Funcionário", example = "Fernando Almeida")
    @NotBlank
    private String nome;
    @Schema(description = "CPF do Funcionário", example = "000.000.000-00")
    @NotBlank
    private String cpf;
    @Schema(description = "Telefone do Funcionário", example = "55 900000000")
    @NotBlank
    private String telefone;
    @Schema(description = "Email do Funcionário", example = "fernando_almeida@gmail.com")
    @NotBlank
    @Email
    private String email;
    @Schema(description = "senha do usuário", example = "123456")
    @NotBlank
    private String senha;
    @NotNull
    private Set<FuncionarioPermissaoRequestDto> permissoes;
}
