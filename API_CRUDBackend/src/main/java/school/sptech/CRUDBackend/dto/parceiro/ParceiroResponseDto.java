package school.sptech.CRUDBackend.dto.parceiro;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para respsota de um parceiro.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParceiroResponseDto {
    private Integer idParceiro;
    @Schema(description = "Fornecedor ou Costureira", example = "Fornecedor")
    private String categoria;
    @Schema(description = "Nome do parceiro", example = "Tecidos do Gil")
    private String nome;
    @Schema(description = "Telefone do parceiro", example = "DD 00000-0000")
    private String telefone;
    @Schema(description = "Email do parceiro", example = "costureira@gmail.com")
    private String email;
    @Schema(description = "Endereço do parceiro", example = " Rua Haddock Lobo, 595 - Cerqueira César, São Paulo - SP, 01414-001")
    private String endereco;
    @Schema(description = "", example = "")
    private String identificacao;
}
