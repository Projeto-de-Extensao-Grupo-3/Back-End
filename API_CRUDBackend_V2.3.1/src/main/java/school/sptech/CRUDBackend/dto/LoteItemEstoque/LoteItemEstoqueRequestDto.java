package school.sptech.CRUDBackend.dto.LoteItemEstoque;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteItemEstoqueRequestDto {

    @NotNull
    private Integer qtdItem;
    @NotNull
    @Min(1)
    private Double preco;
}
