
package school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario;

import school.sptech.CleanArchitecture.core.application.command.funcionario.CriarFuncionarioCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Permissao;

import java.util.Set;
import java.util.stream.Collectors;

public class FuncionarioMapper {

    public static CriarFuncionarioCommand toCriarCommand(FuncionarioRequestDto dto) {
        Set<Permissao> permissoes = dto.getPermissoes().stream()
                .map(p -> new Permissao(p.getIdPermissao(), p.getDescricao()))
                .collect(Collectors.toSet());

        return new CriarFuncionarioCommand(
                dto.getNome(),
                dto.getCpf(),
                dto.getTelefone(),
                dto.getEmail(),
                dto.getSenha(),
                permissoes
        );
    }

    public static FuncionarioResponseDto toResponseDto(Funcionario funcionario) {
        var permissoes = funcionario.getPermissoes() == null ? Set.<PermissaoResponseDto>of() :
                funcionario.getPermissoes().stream()
                        .map(p -> new PermissaoResponseDto(p.getDescricao()))
                        .collect(Collectors.toSet());
        return new FuncionarioResponseDto(
                funcionario.getIdFuncionario(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getTelefone(),
                funcionario.getEmail(),
                permissoes
        );
    }
}
