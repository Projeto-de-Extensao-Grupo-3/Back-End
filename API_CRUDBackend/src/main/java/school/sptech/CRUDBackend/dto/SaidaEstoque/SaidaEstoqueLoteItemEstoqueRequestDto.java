package school.sptech.CRUDBackend.dto.SaidaEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaidaEstoqueLoteItemEstoqueRequestDto {
    @Schema(description = "ID do lote item estoque", example = "1")
    private Integer idLoteItemEstoque;
}
