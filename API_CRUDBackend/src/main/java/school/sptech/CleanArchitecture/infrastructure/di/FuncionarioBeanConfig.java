
package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.adapters.JwtProviderGateway;
import school.sptech.CleanArchitecture.core.adapters.PasswordEncoderGateway;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.AutenticarFuncionarioUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.CadastrarFuncionarioUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioAdapter;

@Configuration
public class FuncionarioBeanConfig {

    @Bean
    public CadastrarFuncionarioUseCase cadastrarFuncionarioUseCase(FuncionarioAdapter adapter,
                                                                   PasswordEncoderGateway passwordEncoderGateway) {
        return new CadastrarFuncionarioUseCase(adapter, passwordEncoderGateway);
    }

    @Bean
    public AutenticarFuncionarioUseCase autenticarFuncionarioUseCase(FuncionarioAdapter adapter,
                                                                     PasswordEncoderGateway passwordEncoderGateway,
                                                                     JwtProviderGateway jwtProviderGateway) {
        return new AutenticarFuncionarioUseCase(adapter, passwordEncoderGateway, jwtProviderGateway);
    }
}
