package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.corteTecido.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.corteTecido.CorteTecidoAdapter;

@Configuration
public class CorteTecidoBeanConfig {

    @Bean
    public AtualizarCorteTecidoPorIdUseCase atualizarCorteTecidoPorIdUseCase(CorteTecidoAdapter adapter) {
        return new AtualizarCorteTecidoPorIdUseCase(adapter);
    }

    @Bean
    public BuscarCorteTecidoPorIdUseCase buscarCorteTecidoPorIdUseCase(CorteTecidoAdapter adapter) {
        return new BuscarCorteTecidoPorIdUseCase(adapter);
    }

    @Bean
    public CadastrarCorteTecidoUseCase cadastrarCorteTecidoUseCase(CorteTecidoAdapter adapter) {
        return new CadastrarCorteTecidoUseCase(adapter);
    }

    @Bean
    public DeletarCorteTecidoPorIdUseCase deletarCorteTecidoPorIdUseCase(CorteTecidoAdapter adapter) {
        return new DeletarCorteTecidoPorIdUseCase(adapter);
    }

    @Bean
    public ListarTodosCorteTecidoUseCase listarTodosCorteTecidoUseCase(CorteTecidoAdapter adapter) {
        return new ListarTodosCorteTecidoUseCase(adapter);
    }
}
