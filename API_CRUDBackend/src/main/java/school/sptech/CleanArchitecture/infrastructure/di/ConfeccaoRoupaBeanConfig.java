package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa.ConfeccaoRoupaAtualizarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa.ConfeccaoRoupaCadastrarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa.ConfeccaoRoupaDeletarUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa.ConfeccaoRoupaAdapter;

@Configuration
public class ConfeccaoRoupaBeanConfig {

    @Bean
    public ConfeccaoRoupaAtualizarUseCase confeccaoRoupaAtualizarUseCase(ConfeccaoRoupaAdapter adapter){
        return new ConfeccaoRoupaAtualizarUseCase(adapter);
    }

    @Bean
    public ConfeccaoRoupaCadastrarUseCase confeccaoRoupaCadastrarUseCase(ConfeccaoRoupaAdapter adapter){
        return new ConfeccaoRoupaCadastrarUseCase(adapter);
    }

    @Bean
    public ConfeccaoRoupaDeletarUseCase confeccaoRoupaDeletarUseCase(ConfeccaoRoupaAdapter adapter){
        return new ConfeccaoRoupaDeletarUseCase(adapter);
    }
}
