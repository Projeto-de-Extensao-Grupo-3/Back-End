package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.alerta.CriarAlertaUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.alerta.AlertaAdapter;

@Configuration
public class AlertaBeanConfig {

    @Bean
    public CriarAlertaUseCase criarAlertaUseCase(AlertaAdapter alertaAdapter){
        return new CriarAlertaUseCase(alertaAdapter);
    }

}
