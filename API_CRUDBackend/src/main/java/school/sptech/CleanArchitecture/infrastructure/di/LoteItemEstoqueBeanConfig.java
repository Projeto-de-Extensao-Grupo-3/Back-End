package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.config.redis.SaidaPaginacaoService;
import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueAtualizarDadosUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueAtualizarQuantidadeUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueBuscarPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque.*;
import school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque.PecasMaiorMaoObraDtoUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueAdapter;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueAdapter;

@Configuration
public class LoteItemEstoqueBeanConfig {

    @Bean
    public AtualizarPorIdloteItemEstoqueUseCase atualizarPorIdloteItemEstoqueUseCase(LoteItemEstoqueAdapter adapter) {
        return new AtualizarPorIdloteItemEstoqueUseCase(adapter);
    }

    @Bean
    public BuscarPorIdLoteItemEstoqueUseCase buscarPorIdLoteItemEstoqueUseCase(LoteItemEstoqueAdapter adapter) {
        return new BuscarPorIdLoteItemEstoqueUseCase(adapter);
    }

    @Bean
    public BuscarTodosLoteItemEstoqueUseCase buscarTodosLoteItemEstoqueUseCase(LoteItemEstoqueAdapter adapter) {
        return new BuscarTodosLoteItemEstoqueUseCase(adapter);
    }

    @Bean
    public CadastrarLoteItemEstoqueUseCase cadastrarLoteItemEstoqueUseCase
            (LoteItemEstoqueGateway gateway, ItemEstoqueAtualizarQuantidadeUseCase itemEstoqueAtualizarUseCase, ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarUseCase, SaidaPaginacaoService redisService) {
        return new CadastrarLoteItemEstoqueUseCase(gateway, redisService, itemEstoqueAtualizarUseCase, itemEstoqueBuscarUseCase);
    }

    @Bean
    public ItemEstoqueAtualizarDadosUseCase atualizarDadosUseCase(ItemEstoqueAdapter adapter, ItemEstoqueBuscarPorIdUseCase buscarPorIdUseCase){
        return new ItemEstoqueAtualizarDadosUseCase(adapter, buscarPorIdUseCase);
    }

    @Bean
    public RemoverLoteItemEstoqueUseCase removerLoteItemEstoqueUseCase (LoteItemEstoqueAdapter adapter) {
        return new RemoverLoteItemEstoqueUseCase(adapter);
    }

    @Bean
    public MargemLucroProdutoUseCase margemLucroProdutoUseCase(LoteItemEstoqueAdapter adapter) {
        return new MargemLucroProdutoUseCase(adapter);
    }

    @Bean
    public PecasMaiorMaoObraDtoUseCase pecasMaiorMaoObraDtoUseCase(LoteItemEstoqueAdapter adapter) {
        return new PecasMaiorMaoObraDtoUseCase(adapter);
    }
}