package school.sptech.CRUDBackend.dto.dtoLogin;

import school.sptech.CRUDBackend.entity.Funcionario;

public class FuncionarioMapper {

    public static Funcionario of(FuncionarioCriacaoDto funcionarioCriacaoDto) {
        Funcionario funcionario = new Funcionario();

        funcionario.setEmail(funcionarioCriacaoDto.getEmail());
        funcionario.setNome(funcionarioCriacaoDto.getNome());
        funcionario.setSenha(funcionarioCriacaoDto.getSenha());

        return funcionario;
    }

    public static Funcionario of(FuncionarioLoginDto funcionarioLoginDto) {
        Funcionario funcionario = new Funcionario();

        funcionario.setEmail(funcionarioLoginDto.getEmail());
        funcionario.setSenha(funcionarioLoginDto.getSenha());

        return funcionario;
    }

    public static FuncionarioTokenDto of(Funcionario funcionario, String token) {
        FuncionarioTokenDto funcionarioTokenDto = new FuncionarioTokenDto();

        funcionarioTokenDto.setIdFuncionario(funcionarioTokenDto.getIdFuncionario());
        funcionarioTokenDto.setEmail(funcionario.getEmail());
        funcionarioTokenDto.setNome(funcionario.getNome());
        funcionarioTokenDto.setToken(token);

        return funcionarioTokenDto;
    }

    public static FuncionarioListarDto of(Funcionario funcionario) {
        FuncionarioListarDto funcionarioListarDto = new FuncionarioListarDto();

        funcionarioListarDto.setIdFuncionario(funcionario.getIdFuncionario());
        funcionarioListarDto.setEmail(funcionario.getEmail());
        funcionarioListarDto.setNome(funcionario.getNome());

        return funcionarioListarDto;
    }
}
