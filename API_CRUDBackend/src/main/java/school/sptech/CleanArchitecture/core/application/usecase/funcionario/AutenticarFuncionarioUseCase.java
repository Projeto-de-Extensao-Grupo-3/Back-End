
package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.adapters.JwtProviderGateway;
import school.sptech.CleanArchitecture.core.adapters.PasswordEncoderGateway;
import school.sptech.CleanArchitecture.core.application.command.funcionario.LoginFuncionarioCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

public class AutenticarFuncionarioUseCase {

    private final FuncionarioGateway gateway;
    private final PasswordEncoderGateway passwordEncoder;
    private final JwtProviderGateway jwtProvider;

    public AutenticarFuncionarioUseCase(FuncionarioGateway gateway,
                                        PasswordEncoderGateway passwordEncoder,
                                        JwtProviderGateway jwtProvider) {
        this.gateway = gateway;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String executar(LoginFuncionarioCommand command) {
        Funcionario funcionario = gateway.findByEmail(command.email())
                .orElseThrow(() -> new RuntimeException("Usuário inválido"));

        if (!passwordEncoder.matches(command.senha(), funcionario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return jwtProvider.generateToken(funcionario);
    }
}
