package school.sptech.CRUDBackend.dto.itemEstoque;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Positive
    private Double qtdMinima;
    @NotBlank
    @Positive
    private Double qtdArmazenado;
}
