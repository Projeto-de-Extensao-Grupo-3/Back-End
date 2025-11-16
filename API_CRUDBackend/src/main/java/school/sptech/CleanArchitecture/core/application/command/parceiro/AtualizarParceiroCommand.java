package school.sptech.CleanArchitecture.core.application.command.parceiro;

import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.TelefoneVo;

public record AtualizarParceiroCommand(
        Integer id,
        String categoria,
        String nome,
        TelefoneVo telefone,
        EmailVo email,
        String endereco,
        String identificacao
){
}
