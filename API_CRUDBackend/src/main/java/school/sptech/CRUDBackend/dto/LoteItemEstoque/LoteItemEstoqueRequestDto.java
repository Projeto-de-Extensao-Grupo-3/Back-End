package school.sptech.CRUDBackend.dto.LoteItemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "DTO para requisição de um objeto LoteItemEstoque.")
@Getter
@Setter
public class LoteItemEstoqueRequestDto {
    @Schema(description = "Quantidade de item que chegou no Lote.", example = "100")
    @NotNull
    private Integer qtdItem;
    @Schema(description = "Preço que o Item está valendo nesse lote.", example = "35")
    @NotNull
    @Min(1)
    private Double preco;
    private LoteItemEstoqueItemRequestDto itemEstoque;
    private LoteItemEstoqueLoteRequestDto lote;
}
