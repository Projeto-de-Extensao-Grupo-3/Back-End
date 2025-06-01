package school.sptech.CRUDBackend.dto.alerta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertaItemEstoqueDto {
    @NotNull
    private Integer idItemEstoque;
    @NotBlank
    private String descricao;
    private String complemento;
    @NotNull
    private Double qtdMinimo;
    @NotNull
    private Double qtdArmazenado;
}
