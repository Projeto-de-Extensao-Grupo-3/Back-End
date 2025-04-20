package school.sptech.CRUDBackend.dto.Lote;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "DTO para requisição de um Lote.")
@Getter
@Setter
@AllArgsConstructor
public class LoteRequestDto {
    @Schema(description = "Entrada de lote de tecido ou roupa", example = "Lote de Roupa Costureira")
    @NotBlank
    private String descricao;
    @Schema(description = "Data de chegada do Lote", example = "2025-04-12T10:15:30")
    @NotBlank
    private String dataEntrada;
    private LoteServicoTerceiroRequestDto servicoTerceiro;
    private LoteFuncionarioRequestDto responsavel;
}
