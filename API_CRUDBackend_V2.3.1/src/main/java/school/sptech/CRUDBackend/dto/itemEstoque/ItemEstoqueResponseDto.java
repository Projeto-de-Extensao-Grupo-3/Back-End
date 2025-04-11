package school.sptech.CRUDBackend.dto.itemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoqueResponseDto {
    private Integer id;
    private String categoria;
    private String descricao;
    private String complemento;
    private Double peso;
    private Double qtdMinima;
    private Double qtdArmazenado;
}
