package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteItemEstoqueLoteRequestDto {
    @Schema(description = "ID do lote", example = "1")
    private Integer idLote;
}
