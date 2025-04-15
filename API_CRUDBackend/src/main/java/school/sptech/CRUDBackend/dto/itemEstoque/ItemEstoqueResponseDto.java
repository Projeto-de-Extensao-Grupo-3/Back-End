package school.sptech.CRUDBackend.dto.itemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para resposta de um objeto ItemEstoque.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoqueResponseDto {
    private Integer id;
    @Schema(description = "Tecido ou Roupa", example = "Tecido")
    private String categoria;
    @Schema(description = "Descrição do item", example = "")
    private String descricao;
    @Schema(description = "Complemento do item", example = "")
    private String complemento;
    @Schema(description = "Peso do item", example = "")
    private Double peso;
    @Schema(description = "Quantidade mínima para item.", example = "100")
    private Double qtdMinima;
    @Schema(description = "Quantidade que ainda possui no estoque.", example = "100")
    private Double qtdArmazenado;
}
