package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.permissao.ListarTodasPermissoesUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.permissao.PermissaoAdapter;

@Configuration
public class PermissaoBeanConfig {

    @Bean
    ListarTodasPermissoesUseCase listarTodasPermissoesUseCase(PermissaoAdapter permissaoAdapter){
        return new ListarTodasPermissoesUseCase(permissaoAdapter);
    }
}
