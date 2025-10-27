package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueCalcularCustoProducaoUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueAdapter;

@Configuration
public class ItemEstoqueBeanConfig {

    @Bean
    public ItemEstoqueAtualizarPorIdUseCase itemEstoqueAtualizarPorIdUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueAtualizarPorIdUseCase(adapter);
    }

    @Bean
    public ItemEstoqueAtualizarQuantidadeUseCase itemEstoqueAtualizarQuantidadeUseCase(ItemEstoqueAtualizarPorIdUseCase atualizarPorIdUseCase){
        return new ItemEstoqueAtualizarQuantidadeUseCase(atualizarPorIdUseCase);
    }

    @Bean
    public ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarPorIdUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueBuscarPorIdUseCase(adapter);
    }

    @Bean
    public ItemEstoqueCadastrarItemUseCase itemEstoqueCadastrarItemUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueCadastrarItemUseCase(adapter);
    }

    @Bean
    public ItemEstoqueCadastrarTecidoRoupaUseCase itemEstoqueCadastrarTecidoRoupaUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueCadastrarTecidoRoupaUseCase(adapter);
    }

    @Bean
    public ItemEstoqueListAllUseCase itemEstoqueListAllUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueListAllUseCase(adapter);
    }

    @Bean
    public ItemEstoqueRemoverPorIdUseCase itemEstoqueRemoverPorIdUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueRemoverPorIdUseCase(adapter);
    }

    @Bean
    public ItemEstoqueBuscarPorDescricaoUseCase itemEstoqueBuscarPorDescricaoUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueBuscarPorDescricaoUseCase(adapter);
    }

    @Bean
    public ItemEstoqueBuscarPorTipoUseCase itemEstoqueBuscarPorTipoUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueBuscarPorTipoUseCase(adapter);
    }

    @Bean
    public ItemEstoqueCalcularCustoProducaoUseCase itemEstoqueCalcularCustoProducaoUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueCalcularCustoProducaoUseCase(adapter);
    }

    @Bean ItemEstoqueListarItensCategoriaUseCase itemEstoqueListarItensCategoriaUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueListarItensCategoriaUseCase(adapter);
    }
}
