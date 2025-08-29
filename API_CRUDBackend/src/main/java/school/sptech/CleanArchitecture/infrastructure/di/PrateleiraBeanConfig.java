package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.prateleira.CriarPrateleiraUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.prateleira.PrateleiraBuscarPorCodigoUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.prateleira.PrateleiraBuscarPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.prateleira.PrateleiraListarAllUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira.PrateleiraAdapter;

@Configuration
public class PrateleiraBeanConfig {

    @Bean
    public CriarPrateleiraUseCase criarPrateleiraUseCase(PrateleiraAdapter prateleiraAdapter){
        return new CriarPrateleiraUseCase(prateleiraAdapter);
    }

    @Bean
    public PrateleiraListarAllUseCase prateleiraListarAllUseCase(PrateleiraAdapter prateleiraAdapter){
        return new PrateleiraListarAllUseCase(prateleiraAdapter);
    }

    @Bean
    public PrateleiraBuscarPorIdUseCase prateleiraBuscarPorIdUseCase(PrateleiraAdapter prateleiraAdapter){
        return new PrateleiraBuscarPorIdUseCase(prateleiraAdapter);
    }

    @Bean
    public PrateleiraBuscarPorCodigoUseCase prateleiraBuscarPorCodigoUseCase(PrateleiraAdapter prateleiraAdapter){
        return new PrateleiraBuscarPorCodigoUseCase(prateleiraAdapter);
    }

}
