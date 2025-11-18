package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteItemEstoqueItemResponseDto {
    private Integer idItemEstoque;
    private String descricao;
    private Double qtdArmazenado;
    private ItemEstoque itemEstoque;
    private Lote lote;
}
