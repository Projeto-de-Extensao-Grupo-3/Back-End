
package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.CleanArchitecture.config.jwt.TokenGatewayAdapter;
import school.sptech.CleanArchitecture.core.adapters.TokenGateway;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.AutenticarFuncionarioUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.CadastrarFuncionarioUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioAdapter;

@Configuration
public class FuncionarioBeanConfig {

    @Bean
    public CadastrarFuncionarioUseCase cadastrarFuncionarioUseCase(FuncionarioAdapter adapter, PasswordEncoder passwordEncoder) {
        return new CadastrarFuncionarioUseCase(adapter, passwordEncoder);
    }

    @Bean
    public AutenticarFuncionarioUseCase autenticarFuncionarioUseCase(FuncionarioAdapter adapter,
                                                                     TokenGateway tokenGateway,
                                                                     AuthenticationManager authenticationManager) {
        return new AutenticarFuncionarioUseCase(adapter, tokenGateway, authenticationManager);
    }
}
