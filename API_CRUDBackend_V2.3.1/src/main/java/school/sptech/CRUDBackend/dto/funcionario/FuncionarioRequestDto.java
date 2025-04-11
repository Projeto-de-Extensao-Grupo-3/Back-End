package school.sptech.CRUDBackend.dto.funcionario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequestDto {
    @NotBlank
    private String nome;
    @NotBlank
    @CPF
    private String cpf;
    @NotBlank
    private String telefone;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String senha;
}
