package school.sptech.CleanArchitecture.core.application.command.corteTecido;

import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;

public record CriarCorteTecidoCommand (
        String inicio,
        String temino,
        Integer funcionario,
        Integer loteItemEstoque
){
}
