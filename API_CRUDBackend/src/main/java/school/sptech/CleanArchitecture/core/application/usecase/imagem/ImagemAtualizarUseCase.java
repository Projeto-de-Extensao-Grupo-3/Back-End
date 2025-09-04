package school.sptech.CleanArchitecture.core.application.usecase.imagem;

import school.sptech.CleanArchitecture.core.adapters.ImagemGateway;
import school.sptech.CleanArchitecture.core.application.command.imagem.CriarImagemCommand;
import school.sptech.CleanArchitecture.core.application.command.imagem.ImagemAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.exception.imagem.ImagemConflitoException;
import school.sptech.CleanArchitecture.core.application.exception.imagem.ImagemNaoEncontradaexception;
import school.sptech.CleanArchitecture.core.application.mapper.ImagemMapper;
import school.sptech.CleanArchitecture.core.domain.entity.Imagem;

public class ImagemAtualizarUseCase {

    private final ImagemGateway gateway;

    public ImagemAtualizarUseCase(ImagemGateway gateway) {
        this.gateway = gateway;
    }

    public Imagem execute(ImagemAtualizarCommand command){
        if(gateway.existsById(command.id())){
            if (gateway.existsByUrl(command.url())){
                throw new ImagemConflitoException("Imagem com URL já cadastrada.");
            }
            Imagem imagemParaAtualizar = ImagemMapper.ofAtualizarImagemCommand(command);
            return gateway.save(imagemParaAtualizar);
        }
        throw new ImagemNaoEncontradaexception("Imagem com url "+ command.url()+" não foi encontrada.");
    }

}
