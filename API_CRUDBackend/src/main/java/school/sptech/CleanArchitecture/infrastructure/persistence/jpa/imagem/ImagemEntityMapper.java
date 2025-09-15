package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem;

import school.sptech.CleanArchitecture.core.application.command.imagem.CriarImagemCommand;
import school.sptech.CleanArchitecture.core.application.command.imagem.ImagemAtualizarCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Imagem;
import school.sptech.CleanArchitecture.infrastructure.web.dto.imagem.ImagemRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.imagem.ImagemResponseDto;

public class ImagemEntityMapper {
    public static Imagem ofEntity(ImagemEntity entity) {
        Imagem domain = new Imagem();
        domain.setIdImagem(entity.getIdImagem());
        domain.setUrl(entity.getUrl());
        return domain;
    }

    public static ImagemEntity ofDomain(Imagem imagem) {
        ImagemEntity entity = new ImagemEntity();
        entity.setIdImagem(imagem.getIdImagem());
        entity.setUrl(imagem.getUrl());
        return entity;
    }

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
