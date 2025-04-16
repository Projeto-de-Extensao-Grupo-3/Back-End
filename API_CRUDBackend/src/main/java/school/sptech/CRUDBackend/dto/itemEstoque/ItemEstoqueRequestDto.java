package school.sptech.CRUDBackend.dto.itemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para requisição de um objeto ItemEstoque.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoqueRequestDto {
    @Schema(description = "Tecido ou Roupa", example = "Tecido")
    @NotBlank
    private String categoria;
    @Schema(description = "Descrição do item", example = "Tecidos: jeans, dry-fit | Roupas: calça, camiseta")
    @NotBlank
    private String descricao;
    @Schema(description = "Complemento do item / Estampa", example = "listrado, vermelho, azul")
    @NotBlank
    private String complemento;
    @Schema(description = "Peso do item", example = "")
    @NotBlank
    private Double peso;
    @Schema(description = "Quantidade mínima para item.", example = "100")
    @NotBlank
    @Positive
    private Double qtdMinima;
    @Schema(description = "Quantidade que ainda possui no estoque.", example = "100")
    @NotBlank
    @Positive
    private Double qtdArmazenado;
}
