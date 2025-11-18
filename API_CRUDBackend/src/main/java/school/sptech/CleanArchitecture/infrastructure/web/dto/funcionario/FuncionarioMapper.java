
package school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario;

import jakarta.validation.Valid;
import school.sptech.CleanArchitecture.core.application.command.funcionario.CriarFuncionarioCommand;
import school.sptech.CleanArchitecture.core.application.command.funcionario.FuncionarioAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.funcionario.FuncionarioAtualizarRequestDto;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Permissao;
import school.sptech.CleanArchitecture.core.domain.valueObject.CpfVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.TelefoneVo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FuncionarioMapper {

    public static CriarFuncionarioCommand toCriarCommand(FuncionarioRequestDto dto) {
        Set<Permissao> permissoes = dto.getPermissoes().stream()
                .map(p -> new Permissao(p.getIdPermissao(), p.getDescricao()))
                .collect(Collectors.toSet());

        return new CriarFuncionarioCommand(
                dto.getNome(),
                new CpfVo(dto.getCpf()),
                new TelefoneVo(dto.getTelefone()),
                new EmailVo(dto.getEmail()),
                dto.getSenha(),
                permissoes
        );
    }

    public static FuncionarioResponseDto toResponseDto(Funcionario funcionario) {
        var permissoes = funcionario.getPermissoes() == null ? Set.<PermissaoResponseDto>of() :
                funcionario.getPermissoes().stream()
                        .map(p -> new PermissaoResponseDto(p.getIdPermissao(), p.getDescricao()))
                        .collect(Collectors.toSet());
        return new FuncionarioResponseDto(
                funcionario.getIdFuncionario(),
                funcionario.getNome(),
                funcionario.getCpf().getValue(),
                funcionario.getTelefone().getValue(),
                funcionario.getEmail().getValue(),
                permissoes
        );
    }



    public static Funcionario ofAtualizarCommand(FuncionarioAtualizarPorIdCommand command) {
        Set<Permissao> permissoes = command.permissoes() != null ? command.permissoes().stream()
                .map(p -> new Permissao(p.getIdPermissao(), p.getDescricao()))
                .collect(Collectors.toSet()) : null;

        return new Funcionario(
                command.idFuncionario(),
                command.nome(),
                new CpfVo(command.cpf().getValue()),
                new TelefoneVo(command.telefone().getValue()),
                new EmailVo(command.email().getValue()),
                permissoes
        );
    }

    public static List<FuncionarioResponseDto> toResponseDtos(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(FuncionarioMapper::toResponseDto)
                .toList();
    }

    public static FuncionarioAtualizarPorIdCommand toAtualizarCommand(Integer id, FuncionarioAtualizarRequestDto dto) {
        Set<Permissao> permissoes = dto.getPermissoes() != null ? dto.getPermissoes().stream()
                .map(p -> new Permissao(p.getIdPermissao(), p.getDescricao()))
                .collect(Collectors.toSet()) : null;

        return new FuncionarioAtualizarPorIdCommand(
                id,
                dto.getNome(),
                new CpfVo(dto.getCpf()),
                new TelefoneVo(dto.getTelefone()),
                new EmailVo(dto.getEmail()),
                permissoes
        );
    }
}
