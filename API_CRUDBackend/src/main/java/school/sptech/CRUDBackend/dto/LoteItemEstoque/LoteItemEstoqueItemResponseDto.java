package school.sptech.CRUDBackend.dto.LoteItemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteItemEstoqueItemResponseDto {
    private Integer idItemEstoque;
    private String descricao;
    private Double qtdArmazenado;
}
