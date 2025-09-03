package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem;


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
        entity.setIdImagem(entity.getIdImagem());
        entity.setUrl(entity.getUrl());
        return entity;
    }
}
