package school.sptech.CleanArchitecture.core.application.mapper;

import school.sptech.CleanArchitecture.core.application.command.imagem.CriarImagemCommand;
import school.sptech.CleanArchitecture.core.application.command.imagem.ImagemAtualizarCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Imagem;

public class ImagemMapper {


    public static Imagem ofAtualizarImagemCommand(ImagemAtualizarCommand command) {
        Imagem imagem = new Imagem();
        imagem.setIdImagem(command.id());
        imagem.setUrl(command.url());
        return imagem;
    }

    public static Imagem ofCriarImagemCommand(CriarImagemCommand command) {
        Imagem imagem = new Imagem();
        imagem.setUrl(command.url());
        return imagem;
    }
}
