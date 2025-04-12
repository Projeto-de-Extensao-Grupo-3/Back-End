package school.sptech.CRUDBackend.dto.funcionario;

import school.sptech.CRUDBackend.entity.Funcionario;

import java.util.List;

public class FuncionarioMapper {
    public static Funcionario toEntity(FuncionarioRequestDto requestDto) {
        return new Funcionario(
                null,
                requestDto.getNome(),
                requestDto.getCpf(),
                requestDto.getTelefone(),
                requestDto.getEmail(),
                requestDto.getSenha()
        );
    }
    
    public static FuncionarioResponseDto toResponseDto(Funcionario funcionario) {
        return new FuncionarioResponseDto(
                funcionario.getIdFuncionario(),
                funcionario.getNome(),
                funcionario.getTelefone(),
                funcionario.getEmail()
        );
    }

    public static List<FuncionarioResponseDto> toResponseDtos(List<Funcionario> funcionarios) {
        return funcionarios
                .stream()
                .map(FuncionarioMapper::toResponseDto)
                .toList();
    }
}
