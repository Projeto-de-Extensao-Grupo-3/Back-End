package school.sptech.CleanArchitecture.core.application.command.alerta;

import java.time.LocalDateTime;

public record AlertaResponseCommand(
         Integer idAlerta,
         String descricao,
         LocalDateTime dataHora,
         AlertaItemEstoqueCommand itemEstoque
){
}
