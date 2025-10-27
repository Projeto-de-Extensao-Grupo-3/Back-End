package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.categoria.*;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueListarItensCategoriaUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaAdapter;

@Configuration
public class CategoriaBeanConfig {

    @Bean
    public CategoriaAtualizarPorIdUseCase categoriaAtualizarPorIdUseCase(CategoriaAdapter categoriaAdapter){
        return new CategoriaAtualizarPorIdUseCase(categoriaAdapter);
    }

    @Bean
    public CategoriaBuscarPorIdUseCase categoriaBuscarPorIdUseCase(CategoriaAdapter categoriaAdapter){
        return new CategoriaBuscarPorIdUseCase(categoriaAdapter);
    }

    @Bean
    public CategoriaBuscarPorNomeUseCase categoriaBuscarPorNomeUseCase(CategoriaAdapter categoriaAdapter){
        return new CategoriaBuscarPorNomeUseCase(categoriaAdapter);
    }

    @Bean
    public CategoriaListAllUseCase categoriaListAllUseCase(CategoriaAdapter categoriaAdapter){
        return new CategoriaListAllUseCase(categoriaAdapter);
    }

    @Bean
    public CategoriaListarPorTipoUseCase categoriaListarPorTipoUseCase(CategoriaAdapter categoriaAdapter){
        return new CategoriaListarPorTipoUseCase(categoriaAdapter);
    }


    @Bean
    public CategoriaRemoverPorId categoriaRemoverPorId(CategoriaAdapter categoriaAdapter, ItemEstoqueListarItensCategoriaUseCase listarItensCategoriaUseCase){
        return new CategoriaRemoverPorId(categoriaAdapter, listarItensCategoriaUseCase);
    }

    @Bean
    public CriarCategoriaUseCase criarCategoriaUseCase(CategoriaAdapter categoriaAdapter){
        return new CriarCategoriaUseCase(categoriaAdapter);
    }
}
