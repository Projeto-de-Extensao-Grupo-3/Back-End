
package school.sptech.CleanArchitecture.infrastructure.web.dto.corteTecido;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para requisição de cadastro de um novo corte de tecido.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorteTecidoRequestDto {
    @NotBlank
//    @PastOrPresent
    @Schema(description = "Data e Hora e inicio do corte", example = "2025-04-12T10:15:30")
    private String inicio;
//    @PastOrPresent
    @Schema(description = "Data e Hora e finalização do corte", example = "2025-04-12T10:15:30")
    private String termino;
    private CorteTecidoFuncionarioRequestDto funcionario;
    private CorteTecidoLoteItemEstoqueRequestDto loteItemEstoque;
}
