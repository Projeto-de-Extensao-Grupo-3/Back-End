package school.sptech.CRUDBackendV1.dto.itemEstoque;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemEstoqueRequestDto {
    @NotBlank
    private String categoria;
    @NotBlank
    private String descricao;
    @NotBlank
    private String complemento;
    @NotBlank
    private Double peso;
    @NotBlank
    private Double qtdMinima;
    @NotBlank
    private Double qtdArmazenado;
}
