
package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.adapters.TokenGateway;
import school.sptech.CleanArchitecture.core.application.command.funcionario.LoginFuncionarioCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario.FuncionarioTokenDto;

public class AutenticarFuncionarioUseCase {

    private final FuncionarioGateway gateway;
    private final TokenGateway tokenGateway;
    private final AuthenticationManager authenticationManager;

    public AutenticarFuncionarioUseCase(FuncionarioGateway gateway, TokenGateway tokenGateway, AuthenticationManager authenticationManager) {
        this.gateway = gateway;
        this.tokenGateway = tokenGateway;
        this.authenticationManager = authenticationManager;
    }

    public FuncionarioTokenDto executar(LoginFuncionarioCommand command) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                command.email(), command.senha()
        );

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Funcionario funcionario = gateway.findByEmail(command.email())
                .orElseThrow(() -> new RuntimeException("Usuário inválido"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = tokenGateway.generateToken(authentication);

        return new FuncionarioTokenDto(
                funcionario.getIdFuncionario(),
                funcionario.getNome(),
                funcionario.getEmail().getValue(),
                token
        );
    }
}
