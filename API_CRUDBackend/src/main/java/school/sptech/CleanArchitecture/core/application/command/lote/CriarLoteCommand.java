package school.sptech.CleanArchitecture.core.application.command.lote;

import java.time.LocalDateTime;

public record CriarLoteCommand(
        String descricao,
        LocalDateTime dataEntrada,
        Integer parceiroId,
        Integer responsavelId
) {
}
