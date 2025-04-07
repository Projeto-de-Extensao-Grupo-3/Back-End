package school.sptech.CRUDBackendV1.dto.itemEstoque;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemEstoqueResponseDto {
    private Integer id;
    private String categoria;
    private String descricao;
    private String complemento;
    private Double peso;
    private Double qtdMinima;
    private Double qtdArmazenado;
}
