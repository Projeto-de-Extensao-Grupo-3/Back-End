package school.sptech.CRUDBackend.dto.LoteItemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "DTO para requisição de um objeto LoteItemEstoque.")
@Getter
@Setter
public class LoteItemEstoqueResponseDto {

    private Integer id;
    @Schema(description = "Quantidade de item que chegou no Lote.", example = "100")
    private Integer qtdItem;
    @Schema(description = "Preço que o Item está valendo nesse lote.", example = "35")
    private Double preco;
}
