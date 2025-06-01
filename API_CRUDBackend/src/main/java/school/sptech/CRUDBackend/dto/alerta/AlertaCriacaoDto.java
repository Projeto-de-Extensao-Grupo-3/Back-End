package school.sptech.CRUDBackend.dto.alerta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertaCriacaoDto {
    @NotBlank
    private String descricao;
    @PastOrPresent
    private LocalDateTime dataHora;
    @NotNull
    private AlertaItemEstoqueDto itemEstoque;
}
