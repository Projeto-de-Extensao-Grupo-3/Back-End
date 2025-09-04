package school.sptech.CleanArchitecture.infrastructure.web.dto.parceiro;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para requisição de Parceiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParceiroRequestDto {

    @Schema(description = "Categoria do parceiro", example = "Costureira")
    @NotBlank
    private String categoria;

    @Schema(description = "Nome do parceiro", example = "Maria Silva")
    @NotBlank
    private String nome;

    @Schema(description = "Telefone do parceiro", example = "(11) 99999-9999")
    private String telefone;

    @Schema(description = "Email do parceiro", example = "maria@email.com")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "Endereço do parceiro", example = "Rua das Flores, 123")
    private String endereco;

    @Schema(description = "Identificação do parceiro (CPF/CNPJ)", example = "123.456.789-00")
    @NotBlank
    private String identificacao;
}
