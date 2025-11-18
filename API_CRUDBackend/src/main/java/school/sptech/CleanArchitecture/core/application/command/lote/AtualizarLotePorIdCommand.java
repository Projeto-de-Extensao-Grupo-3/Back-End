package school.sptech.CleanArchitecture.core.application.command.lote;

import java.time.LocalDateTime;

public record AtualizarLotePorIdCommand(
        Integer idLote,
        String descricao,
        LocalDateTime dataEntrada,
        Integer parceiro,
        Integer responsavel
) {
}
