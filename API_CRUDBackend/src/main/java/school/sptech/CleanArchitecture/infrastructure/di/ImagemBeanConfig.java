package school.sptech.CleanArchitecture.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.CleanArchitecture.core.application.usecase.imagem.ImagemAtualizarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.imagem.ImagemCadastrarUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.imagem.ImagemDeletarPorIdUseCase;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemAdapter;

@Configuration
public class ImagemBeanConfig {

    @Bean
    public ImagemCadastrarUseCase imagemCadastrarUseCase(ImagemAdapter imagemAdapter){
        return new ImagemCadastrarUseCase(imagemAdapter);
    }

    @Bean
    public ImagemAtualizarUseCase imagemAtualizarUseCase(ImagemAdapter imagemAdapter){
        return new ImagemAtualizarUseCase(imagemAdapter);
    }

    @Bean
    public ImagemDeletarPorIdUseCase imagemDeletarPorIdUseCase(ImagemAdapter imagemAdapter){
        return new ImagemDeletarPorIdUseCase(imagemAdapter);
    }

}
