package school.sptech.CRUDBackend.dto.SaidaEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "DTO para resposta de uma SaídaEstoque.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaidaEstoqueResponseDto {

    private Integer idSaida;
    @Schema(description = "Data da saída", example = "2025-04-12")
    private LocalDate data;
    @Schema(description = "Hora da saída", example = "14:30:00")
    private LocalTime hora;
    @Schema(description = "Quantidade do Item que saiu", example = "250")
    private Integer qtSaida;
    @Schema(description = "Se foi para costureira ou venda", example = "Venda direta do Brás.")
    private String motivoSaida;
    private SaidaEstoqueFuncionarioResponseDto funcionario;
    private SaidaEstoqueLoteItemEstoqueResponseDto loteItemEstoque;
    private SaidaEstoqueCostureiraResponseDto costureira;
}
