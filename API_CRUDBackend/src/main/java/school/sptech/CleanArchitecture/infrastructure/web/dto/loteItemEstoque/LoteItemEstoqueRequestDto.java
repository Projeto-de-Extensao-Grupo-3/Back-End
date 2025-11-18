package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;

@Schema(description = "DTO para requisição de um objeto LoteItemEstoque.")
@Getter
@Setter
public class LoteItemEstoqueRequestDto {
    @Schema(description = "Quantidade de item que chegou no Lote.", example = "10.0")
    @NotNull
    private Double qtdItem;
    @Schema(description = "Preço que o Item está valendo nesse lote.", example = "350.0")
    @NotNull
    @Min(1)
    private Double preco;
    private Integer itemEstoque;
    private Integer lote;
}
