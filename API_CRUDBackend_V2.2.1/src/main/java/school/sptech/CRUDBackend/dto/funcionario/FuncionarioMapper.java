package school.sptech.CRUDBackend.dto.funcionario;

import school.sptech.CRUDBackend.entity.Funcionario;

public class FuncionarioMapper {
    public static Funcionario toEntity(FuncionarioRequestDto requestDto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(requestDto.getNome());
        funcionario.setCpf(requestDto.getCpf());
        funcionario.setTelefone(requestDto.getTelefone());
        funcionario.setEmail(requestDto.getEmail());
        funcionario.setSenha(requestDto.getSenha());
        return funcionario;
    }
    
    public static FuncionarioResponseDto toResponseDto(Funcionario funcionario) {
        FuncionarioResponseDto responseDto = new FuncionarioResponseDto();
        responseDto.setIdFuncionario(funcionario.getIdFuncionario());
        responseDto.setNome(funcionario.getNome());
        responseDto.setTelefone(funcionario.getTelefone());
        responseDto.setEmail(funcionario.getEmail());
        return responseDto;
    }
}
