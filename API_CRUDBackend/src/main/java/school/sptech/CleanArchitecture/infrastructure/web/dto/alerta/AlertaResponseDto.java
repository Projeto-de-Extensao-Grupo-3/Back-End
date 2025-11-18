package school.sptech.CleanArchitecture.infrastructure.web.dto.alerta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertaResponseDto {
    private Integer idAlerta;
    private String descricao;
    private LocalDateTime dataHora;
    private AlertaItemEstoqueDto itemEstoque;
}
