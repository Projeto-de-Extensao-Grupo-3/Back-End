package school.sptech.CRUDBackend.dto.parceiro;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para requisição de um novo parceiro.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParceiroRequestDto {
    @Schema(description = "Fornecedor ou Costureira", example = "Fornecedor")
    @NotBlank
    private String categoria;
    @Schema(description = "Nome do parceiro", example = "Tecidos do Gil")
    @NotBlank
    private String nome;
    @Schema(description = "Telefone do parceiro", example = "DD 00000-0000")
    @NotBlank
    private String telefone;
    @Schema(description = "Email do parceiro", example = "costureira@gmail.com")
    @NotBlank
    @Email
    private String email;
    @Schema(description = "Endereço do parceiro", example = " Rua Haddock Lobo, 595 - Cerqueira César, São Paulo - SP, 01414-001")
    @NotBlank
    private String endereco;
    @Schema(description = "", example = "")
    @NotBlank
    private String identificacao;
}
