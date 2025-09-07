package school.sptech.CleanArchitecture.core.application.command.itemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoqueConfeccaoRoupaCommand {
    private Integer idConfeccaoRoupa;
    private ItemEstoqueTecidoCommand tecido;
    private Double qtdTecido;
}
