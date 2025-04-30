package school.sptech.CRUDBackend.dto.LoteItemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteItemEstoqueCadastroDto {
    private Integer id;
    @Schema(description = "Quantidade de item que chegou no Lote.", example = "10.0")
    private Double qtdItem;
    @Schema(description = "Preço que o Item está valendo nesse lote.", example = "250.0")
    private Double preco;
}
