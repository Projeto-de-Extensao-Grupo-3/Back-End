package school.sptech.CleanArchitecture.infrastructure.web.dto.imagem;

import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemEntity;

public class ImagemMapper {

    public static ImagemEntity toEntity(ImagemRequestDto requestDto) {
        return new ImagemEntity(null, requestDto.getUrl());
    }

    public static ImagemResponseDto toResponseDto(ImagemEntity imagem) {
        return new ImagemResponseDto(imagem.getIdImagem(), imagem.getUrl());
    }
}
