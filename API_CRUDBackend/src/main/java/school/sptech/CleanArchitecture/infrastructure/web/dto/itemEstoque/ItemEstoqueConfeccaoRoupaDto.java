package school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoqueConfeccaoRoupaDto {
    private Integer idConfeccaoRoupa;
    private ItemEstoqueTecidoDto tecido;
    private Double qtdTecido;
}
