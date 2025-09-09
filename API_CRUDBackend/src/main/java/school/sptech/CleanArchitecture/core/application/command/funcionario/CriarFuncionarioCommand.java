
package school.sptech.CleanArchitecture.core.application.command.funcionario;

import school.sptech.CleanArchitecture.core.domain.entity.Permissao;

import java.util.Set;

public record CriarFuncionarioCommand(
        String nome,
        String cpf,
        String telefone,
        String email,
        String senha,
        Set<Permissao> permissoes
) { }
