package school.sptech.CleanArchitecture.core.application.command.alerta;

import java.time.LocalDateTime;

public record AlertaCriacaoCommand(
         String descricao,
         LocalDateTime dataHora,
         AlertaItemEstoqueCommand itemEstoque
) {
}
