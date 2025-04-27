package school.sptech.CRUDBackend.dto.SaidaEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaidaEstoqueLoteItemEstoqueResponseDto {
    private Double qtdItem;
    private Double preco;
}
