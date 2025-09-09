package school.sptech.CleanArchitecture.infrastructure.web.dto.loginDto;


import school.sptech.CleanArchitecture.core.application.command.funcionario.LoginFuncionarioCommand;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;

public class FuncionarioLoginMapper {

    public static FuncionarioEntity of(FuncionarioCriacaoDto funcionarioCriacaoDto) {
        FuncionarioEntity funcionario = new FuncionarioEntity();

        funcionario.setEmail(funcionarioCriacaoDto.getEmail());
        funcionario.setNome(funcionarioCriacaoDto.getNome());
        funcionario.setSenha(funcionarioCriacaoDto.getSenha());

        return funcionario;
    }

    public static FuncionarioEntity of(FuncionarioLoginDto funcionarioLoginDto) {
        FuncionarioEntity funcionario = new FuncionarioEntity();

        funcionario.setEmail(funcionarioLoginDto.getEmail());
        funcionario.setSenha(funcionarioLoginDto.getSenha());

        return funcionario;
    }

    public static FuncionarioTokenDto of(FuncionarioEntity funcionario, String token) {
        FuncionarioTokenDto funcionarioTokenDto = new FuncionarioTokenDto();

        funcionarioTokenDto.setEmail(funcionario.getEmail());
        funcionarioTokenDto.setNome(funcionario.getNome());
        funcionarioTokenDto.setToken(token);

        return funcionarioTokenDto;
    }

    public static FuncionarioListarDto of(FuncionarioEntity funcionario) {
        FuncionarioListarDto funcionarioListarDto = new FuncionarioListarDto();

        funcionarioListarDto.setIdFuncionario(funcionario.getIdFuncionario());
        funcionarioListarDto.setEmail(funcionario.getEmail());
        funcionarioListarDto.setNome(funcionario.getNome());

        return funcionarioListarDto;
    }

    public static LoginFuncionarioCommand toLoginCommand(FuncionarioLoginDto dto) {
        return new LoginFuncionarioCommand(
                dto.getEmail(),
                dto.getSenha()
        );
    }
}
