package school.sptech.CleanArchitecture.core.application.command.itemEstoque;

import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;

import java.util.Set;

public record ItemEstoqueCadastrarTecidoRoupaCommand(
        Integer idRoupa,
        Set<ConfeccaoRoupa> confeccaoRoupas
) {
}
