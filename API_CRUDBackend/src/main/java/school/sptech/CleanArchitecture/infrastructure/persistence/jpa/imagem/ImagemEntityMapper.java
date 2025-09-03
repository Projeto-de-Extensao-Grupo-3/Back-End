package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem;


import school.sptech.CleanArchitecture.infrastructure.web.dto.imagem.ImagemRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.imagem.ImagemResponseDto;

public class ImagemEntityMapper {
    public static ImagemEntity toEntity(ImagemRequestDto requestDto) {
        return new ImagemEntity(null, requestDto.getUrl());
    }

    public static ImagemResponseDto toResponseDto(ImagemEntity imagem) {
        return new ImagemResponseDto(imagem.getIdImagem(), imagem.getUrl());
    }
}
