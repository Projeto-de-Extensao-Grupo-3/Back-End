package school.sptech.CRUDBackend.dto.servicoTerceiro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoTerceiroRequestDto {
    @NotBlank
    private String categoria;
    @NotBlank
    private String nome;
    @NotBlank
    private String telefone;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String endereco;
    @NotBlank
    private String identificacao;
}
