package school.sptech.CleanArchitecture.core.application.command.lote;

import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

import java.time.LocalDateTime;

public record AtualizarLotePorIdCommand(
        Integer idLote,
        String descricao,
        LocalDateTime dataEntrada,
        Integer parceiro,
        Integer responsavel
) {
}
