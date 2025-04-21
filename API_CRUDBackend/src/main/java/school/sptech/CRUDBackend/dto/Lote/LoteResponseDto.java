package school.sptech.CRUDBackend.dto.Lote;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Schema(description = "DTO para respsota de um Lote.")
@Getter
@Setter
public class LoteResponseDto {

    private Integer idLote;
    @Schema(description = "Entrada de lote de tecido ou roupa", example = "Lote de Roupa Costureira")
    private String descricao;
    @Schema(description = "Data de chegada do Lote", example = "2025-04-12T10:15:30")
    private LocalDateTime dataEntrada;
}
