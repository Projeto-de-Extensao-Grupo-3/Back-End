package school.sptech.CleanArchitecture.core.application.usecase.imagem;

import school.sptech.CleanArchitecture.core.adapters.ImagemGateway;
import school.sptech.CleanArchitecture.core.application.command.imagem.CriarImagemCommand;
import school.sptech.CleanArchitecture.core.application.exception.imagem.ImagemNaoEncontradaexception;
import school.sptech.CleanArchitecture.core.application.mapper.ImagemMapper;
import school.sptech.CleanArchitecture.core.domain.entity.Imagem;

public class ImagemCadastrarUseCase {

    private final ImagemGateway gateway;

    public ImagemCadastrarUseCase(ImagemGateway gateway) {
        this.gateway = gateway;
    }

    public Imagem execute(CriarImagemCommand command){
        if (gateway.existsByUrl(command.url())){
            throw new ImagemNaoEncontradaexception("Imagem com url "+ command.url()+" não foi encontrada.");
        }
        Imagem imagemParaCadastrar = ImagemMapper.ofCriarImagemCommand(command);
        return gateway.save(imagemParaCadastrar);
    }
}
