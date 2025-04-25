<<<<<<< HEAD
package school.sptech.CRUDBackend.dto.corteTecido;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;

import java.time.LocalDateTime;

@Schema(description = "DTO para requisição de cadastro de um novo corte de tecido.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorteTecidoRequestDto {
    @NotBlank
    @PastOrPresent
    @Schema(description = "Data e Hora e inicio do corte", example = "2025-04-12T10:15:30")
    private LocalDateTime inicio;
    @PastOrPresent
    @Schema(description = "Data e Hora e finalização do corte", example = "2025-04-12T10:15:30")
    private LocalDateTime termino;
    private Funcionario funcionario;
    private LoteItemEstoque loteItemEstoque;
}
=======
package school.sptech.CRUDBackend.dto.corteTecido;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
>>>>>>> 17669764fd71a6b33ecdc795c1a9c9f3024ad124
