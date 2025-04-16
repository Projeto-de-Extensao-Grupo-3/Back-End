package school.sptech.CRUDBackend.dto.funcionario;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @Schema(description = "Nome do Funcionário", example = "Fernando")
    @NotBlank
    private String nome;
    @Schema(description = "CPF do Funcionário", example = "XXX-XXX-XXX/XX")
    @NotBlank
    @CPF
    private String cpf;
    @Schema(description = "Telefone do Funcionário", example = "DD 00000-0000")
    @NotBlank
    private String telefone;
    @Schema(description = "Email do Funcionário", example = "fernando@gmail.com")
    @NotBlank
    @Email
    private String email;
    @Hidden
    @NotBlank
    private String senha;
    @NotBlank
    private Set<Permissao> permissoes;
}
