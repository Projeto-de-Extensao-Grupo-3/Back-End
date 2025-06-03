package school.sptech.CRUDBackend.dto.itemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Schema(description = "DTO para resposta de um objeto ItemEstoque.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoqueResponseDto {
    private Integer idItemEstoque;
    @Schema(description = "Descrição do item", example = "Tecidos: jeans, dry-fit | Roupas: calça, camiseta")
    private String descricao;
    @Schema(description = "Peso do item", example = "")
    private Double peso;
    @Schema(description = "Quantidade mínima para item.", example = "100")
    private Double qtdMinimo;
    @Schema(description = "Quantidade que ainda possui no estoque.", example = "100")
    private Double qtdArmazenado;
    private ItemEstoqueCategoriaResponseDto subcategoria;
    private Set<ItemEstoqueCaracteristicaResponseDto> caracteriticas;
    private Set<ItemEstoqueConfeccaoRoupaDto> confeccaoRoupa;
    private Double preco;
}
