package school.sptech.CleanArchitecture.core.application.command.itemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoqueTecidoCommand {
    private Integer idTecido;
    private String descricao;
}
