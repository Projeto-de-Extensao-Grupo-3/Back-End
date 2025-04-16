package school.sptech.CRUDBackend.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.Permissao;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(description = "Classe de mapeamento de DTOs Funcionario.")
public class FuncionarioMapper {

    public static Funcionario toEntity(FuncionarioRequestDto requestDto) {
        Set<Permissao> permissoes = requestDto.getPermissoes()
                .stream()
                .map(permissao
                        -> new Permissao(permissao.getIdPermissao(), permissao.getDescricao()))
                .collect(Collectors.toSet());

        return new Funcionario(
                null,
                requestDto.getNome(),
                requestDto.getCpf(),
                requestDto.getTelefone(),
                requestDto.getEmail(),
                requestDto.getSenha(),
                permissoes
        );
    }
    
    public static FuncionarioResponseDto toResponseDto(Funcionario funcionario) {
        Set<FuncionarioPermissaoResponseDto> permissoes = funcionario.getPermissoes()
                .stream()
                .map(permissao
                        -> new FuncionarioPermissaoResponseDto(permissao.getDescricao()))
                .collect(Collectors.toSet());

        return new FuncionarioResponseDto(
                funcionario.getIdFuncionario(),
                funcionario.getNome(),
                funcionario.getTelefone(),
                funcionario.getEmail(),
                permissoes
        );
    }

    public static List<FuncionarioResponseDto> toResponseDtos(List<Funcionario> funcionarios) {
        return funcionarios
                .stream()
                .map(FuncionarioMapper::toResponseDto)
                .toList();
    }
}
