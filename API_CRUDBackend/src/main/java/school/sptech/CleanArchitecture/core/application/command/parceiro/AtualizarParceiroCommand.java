package school.sptech.CleanArchitecture.core.application.command.parceiro;

import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;

public record AtualizarParceiroCommand(
        Integer id,
        String categoria,
        String nome,
        String telefone,
        EmailVo email,
        String endereco,
        String identificacao
){
}
