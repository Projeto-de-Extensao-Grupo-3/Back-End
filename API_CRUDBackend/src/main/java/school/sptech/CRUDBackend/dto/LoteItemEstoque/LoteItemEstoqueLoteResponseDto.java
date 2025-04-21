package school.sptech.CRUDBackend.dto.LoteItemEstoque;

import jakarta.persistence.Entity;
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
    private String dataEntrada;
}
