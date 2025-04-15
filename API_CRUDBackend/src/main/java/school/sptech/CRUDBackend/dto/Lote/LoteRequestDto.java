package school.sptech.CRUDBackend.dto.Lote;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "DTO para requisição de um Lote.")
@Getter
@Setter
public class LoteRequestDto {

    @Schema(description = "", example = "")
    @NotBlank
    private String descricao;
    @Schema(description = "Data de chegada do Lote", example = "2025-04-12T10:15:30")
    @NotBlank
    @PastOrPresent
    private LocalDateTime dataEntrada;

}
