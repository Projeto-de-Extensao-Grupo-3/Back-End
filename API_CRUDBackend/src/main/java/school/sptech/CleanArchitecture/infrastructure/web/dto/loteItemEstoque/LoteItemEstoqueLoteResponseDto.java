package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoteItemEstoqueLoteResponseDto {
    private Integer idLote;
    private String descricao;
}
