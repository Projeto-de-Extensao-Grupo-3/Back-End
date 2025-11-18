
package school.sptech.CleanArchitecture.core.application.command.funcionario;

import school.sptech.CleanArchitecture.core.domain.entity.Permissao;
import school.sptech.CleanArchitecture.core.domain.valueObject.CpfVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.TelefoneVo;

import java.util.Set;

public record CriarFuncionarioCommand(
        String nome,
        CpfVo cpf,
        TelefoneVo telefone,
        EmailVo email,
        String senha,
        Set<Permissao> permissoes
) { }
