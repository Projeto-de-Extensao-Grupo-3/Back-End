
package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.CRUDBackend.config.GerenciadorTokenJwt;
import school.sptech.CleanArchitecture.config.jwt.TokenGatewayAdapter;
import school.sptech.CleanArchitecture.core.adapters.EmailService;
import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.adapters.TokenGateway;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioAdapter;

@Configuration
public class FuncionarioBeanConfig {

    @Bean
    public FuncionarioBuscarPorEmailUseCase funcionarioBuscarPorEmailUseCase(FuncionarioAdapter adapter){
        return new FuncionarioBuscarPorEmailUseCase(adapter);
    }

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

    @Bean
    public FuncionarioAtualizarPorIdUseCase funcionarioAtualizarPorIdUseCase(FuncionarioAdapter adapter, BuscarFuncionarioPorIdUseCase buscarFuncionarioPorIdUseCase){
        return new FuncionarioAtualizarPorIdUseCase(adapter, buscarFuncionarioPorIdUseCase);
    }

    @Bean
    public FuncionarioAtualizarSenhaUseCase funcionarioAtualizarSenhaUseCase(FuncionarioAdapter adapter,  BuscarFuncionarioPorIdUseCase buscarFuncionarioPorIdUseCase, PasswordEncoder passwordEncoder){
        return new FuncionarioAtualizarSenhaUseCase(adapter, buscarFuncionarioPorIdUseCase, passwordEncoder);
    }

    @Bean
    public FuncionarioBuscarPorNomeUseCAse funcionarioBuscarPorNomeUseCAse(FuncionarioAdapter adapter){
        return new FuncionarioBuscarPorNomeUseCAse(adapter);
    }

    @Bean
    public BuscarFuncionarioPorIdUseCase buscarFuncionarioPorIdUseCase(FuncionarioAdapter adapter){
        return new BuscarFuncionarioPorIdUseCase(adapter);
    }

    @Bean
    public FuncionarioListAllUseCase funcionarioListAllUseCase(FuncionarioAdapter adapter){
        return new FuncionarioListAllUseCase(adapter);
    }

    @Bean
    public FuncionarioRemoverPorIdUseCase funcionarioRemoverPorIdUseCase(FuncionarioAdapter adapter){
        return new FuncionarioRemoverPorIdUseCase(adapter);
    }

    @Bean
    public FuncionarioListarPorPermissaoUserCase funcionarioListarPorPermissaoUserCase(FuncionarioAdapter adapter){
        return new FuncionarioListarPorPermissaoUserCase(adapter);
    }

    @Bean
    public GerenciadorTokenJwt gerenciadorTokenJwt(){
        return new GerenciadorTokenJwt();
    }

    @Bean
    public RecuperarSenhaUseCase recuperarSenhaUseCase(FuncionarioGateway gateway, GerenciadorTokenJwt gerenciadorTokenJwt, EmailService emailService){
        return new RecuperarSenhaUseCase(gateway, gerenciadorTokenJwt, emailService);
    }

    @Bean
    public ResetarSenhaUseCase resetarSenhaUseCase(FuncionarioGateway gateway, GerenciadorTokenJwt gerenciadorTokenJwt, PasswordEncoder passwordEncoder){
        return new ResetarSenhaUseCase(gateway, gerenciadorTokenJwt, passwordEncoder);
    }
}
