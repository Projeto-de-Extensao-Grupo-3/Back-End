package school.sptech.CRUDBackendV1.dto.servicoTerceiro;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
