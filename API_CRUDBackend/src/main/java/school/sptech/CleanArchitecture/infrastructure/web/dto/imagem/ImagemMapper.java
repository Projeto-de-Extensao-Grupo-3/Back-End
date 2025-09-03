package school.sptech.CleanArchitecture.infrastructure.web.dto.imagem;

import school.sptech.CleanArchitecture.core.application.command.imagem.CriarImagemCommand;
import school.sptech.CleanArchitecture.core.application.command.imagem.ImagemAtualizarCommand;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemEntity;

public class ImagemMapper {

    public static ImagemEntity toEntity(ImagemRequestDto requestDto) {
        return new ImagemEntity(null, requestDto.getUrl());
    }

    public static ImagemResponseDto toResponseDto(ImagemEntity imagem) {
        return new ImagemResponseDto(imagem.getIdImagem(), imagem.getUrl());
    }

    public static CriarImagemCommand toCriarCommand(ImagemRequestDto imagemRequestDto){
        return new CriarImagemCommand(imagemRequestDto.getUrl());
    }

    public static ImagemAtualizarCommand toAtualizarCommand(Integer id, ImagemRequestDto imagemRequestDto){
        return new ImagemAtualizarCommand(id, imagemRequestDto.getUrl());
    }
}
