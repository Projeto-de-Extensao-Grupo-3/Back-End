package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para respsota de um Lote.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteResponseDto {
    private Integer idLote;
    @Schema(description = "Entrada de lote de tecido ou roupa", example = "Lote de Roupa Costureira")
    private String descricao;
    @Schema(description = "Data de chegada do Lote", example = "2025-04-12T10:15:30")
    private String dataEntrada;
    private LoteFuncionarioResponseDto responsavel;
    private LoteParceiroResponseDto parceiro;
}
