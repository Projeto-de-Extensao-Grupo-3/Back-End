package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.lote.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteAdapter;

@Configuration
public class LoteBeanConfig {

    @Bean
    public AtualizarLotePorIdUseCase atualizarLotePorIdUseCase(LoteAdapter adapter) {
        return new AtualizarLotePorIdUseCase(adapter);
    }

    @Bean
    public BuscarLotePorIdUseCase buscarLotePorIdUseCase(LoteAdapter adapter) {
        return new BuscarLotePorIdUseCase(adapter);
    }

    @Bean
    public CadastrarLoteUseCase cadastrarLoteUseCase(LoteAdapter adapter) {
        return new CadastrarLoteUseCase(adapter);
    }

    @Bean
    public ListarTodosLotesUseCase listarTodosLotesUseCase(LoteAdapter adapter) {
        return new ListarTodosLotesUseCase(adapter);
    }

    @Bean
    public RemoverLotePorIdUseCase removerLotePorIdUseCase(LoteAdapter adapter) {
        return new RemoverLotePorIdUseCase(adapter);
    }

    @Bean
    public LotesEmEstoqueUseCase lotesEmEstoqueUseCase(LoteAdapter adapter){
        return new LotesEmEstoqueUseCase(adapter);
    }

    @Bean
    public LoteDetalhadoUseCase loteDetalhadoUseCase(LoteAdapter adapter) {
        return new LoteDetalhadoUseCase(adapter);
    }
}
