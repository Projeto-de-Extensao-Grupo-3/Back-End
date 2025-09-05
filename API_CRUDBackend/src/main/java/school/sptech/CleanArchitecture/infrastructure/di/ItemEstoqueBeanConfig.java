package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueAdapter;

@Configuration
public class ItemEstoqueBeanConfig {

    @Bean
    public ItemEstoqueAtualizarPorIdUseCase itemEstoqueAtualizarPorIdUseCase(ItemEstoqueAdapter adapter){
        return new ItemEstoqueAtualizarPorIdUseCase(adapter);
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
}
