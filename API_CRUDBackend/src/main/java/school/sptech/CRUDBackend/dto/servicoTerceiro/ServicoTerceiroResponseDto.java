package school.sptech.CRUDBackend.dto.servicoTerceiro;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para respsota de um serviço terceiro.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoTerceiroResponseDto {
    private Integer idServicoTerceiro;
    @Schema(description = "Fornecedor ou Costureira", example = "Fornecedor")
    private String categoria;
    @Schema(description = "Nome do Serviço", example = "Tecidos do Gil")
    private String nome;
    @Schema(description = "Telefone do Serviço Terceiro", example = "DD 00000-0000")
    private String telefone;
    @Schema(description = "Email do Serviço Terceiro", example = "costureira@gmail.com")
    private String email;
    @Schema(description = "Endereço do Serviço Terceiro", example = " Rua Haddock Lobo, 595 - Cerqueira César, São Paulo - SP, 01414-001")
    private String endereco;
    @Schema(description = "", example = "")
    private String identificacao;
}
