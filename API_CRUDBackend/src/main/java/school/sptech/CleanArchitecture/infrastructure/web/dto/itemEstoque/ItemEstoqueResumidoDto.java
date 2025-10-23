package school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEstoqueResumidoDto {
    private Integer idItem;
    private String descricao;
}
