package school.sptech.CleanArchitecture.infrastructure.web.dto.corteTecido;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public CorteTecidoResponseDto(Integer idCorteTecido, String inicio, String termino) {
        this.idCorteTecido = idCorteTecido;
        this.inicio = inicio;
        this.termino = termino;
    }
}
