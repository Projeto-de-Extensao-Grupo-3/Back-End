package school.sptech.CRUDBackend.dto.alerta;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "ID do ItemEstoque", example = "1")
    @NotNull
    private Integer idItemEstoque;
    @Schema(description = "Descrição do ItemEstoque", example = "Vestido azul florido")
    @NotBlank
    private String descricao;
    private String complemento;
    @NotNull
    private Double qtdMinimo;
    @NotNull
    private Double qtdArmazenado;
}
