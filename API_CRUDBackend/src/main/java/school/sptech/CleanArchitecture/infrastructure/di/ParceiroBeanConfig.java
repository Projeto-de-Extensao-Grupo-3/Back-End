package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.parceiro.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro.ParceiroAdapter;

@Configuration
public class ParceiroBeanConfig {

    @Bean
    public CadastrarParceiroUseCase cadastrarParceiroUseCase(ParceiroAdapter parceiroAdapter) {
        return new CadastrarParceiroUseCase(parceiroAdapter);
    }

    @Bean
    public ListarTodosParceirosUseCase listarTodosParceirosUseCase(ParceiroAdapter parceiroAdapter) {
        return new ListarTodosParceirosUseCase(parceiroAdapter);
    }

    @Bean
    public BuscarParceiroPorNomeUseCase buscarParceiroPorNomeUseCase(ParceiroAdapter parceiroAdapter) {
        return new BuscarParceiroPorNomeUseCase(parceiroAdapter);
    }

    @Bean
    public AtualizarParceiroPorIdUseCase atualizarParceiroPorIdUseCase(ParceiroAdapter parceiroAdapter) {
        return new AtualizarParceiroPorIdUseCase(parceiroAdapter);
    }

    @Bean
    public RemoverParceiroPorIdUseCase removerParceiroPorIdUseCase(ParceiroAdapter parceiroAdapter) {
        return new RemoverParceiroPorIdUseCase(parceiroAdapter);
    }
}
