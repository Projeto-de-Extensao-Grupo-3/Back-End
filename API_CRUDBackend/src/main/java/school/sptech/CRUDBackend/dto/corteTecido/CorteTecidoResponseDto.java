package school.sptech.CRUDBackend.dto.corteTecido;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "DTO para resposta de um corte de tecido.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorteTecidoResponseDto {
    private Integer idCorteTecido;
    @Schema(description = "Data e Hora de inicio do corte", example = "2025-04-12T10:15:30")
    private String inicio;
    @Schema(description = "Data e Hora de finalização do corte", example = "2025-04-12T10:15:30")
    private String termino;

    private CorteTecidoFuncionarioResponseDto funcionario;
}
