package school.sptech.CRUDBackend.dto.SaidaEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "DTO para requisição de uma nova SaídaEstoque.")
@Getter
@Setter
public class SaidaEstoqueRequestDto {

    @Schema(description = "Data da saída", example = "2025-04-12")
    @PastOrPresent
    @NotNull
    private LocalDate data;
    @Schema(description = "Hora da saída", example = "14:30:00")
    @NotNull(message = "O campo hora é obrigatório")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hora;
    @Schema(description = "Quantidade do Item que saiu", example = "250")
    @Min(1)
    private Integer qtSaida;
    @Schema(description = "Se foi para costureira ou venda", example = "Venda direta do Brás.")
    private String motivoSaida;
}
