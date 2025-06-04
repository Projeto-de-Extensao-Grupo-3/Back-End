package school.sptech.CRUDBackend.dto.imagem;

import school.sptech.CRUDBackend.entity.Imagem;

public class ImagemMapper {
    public static Imagem toEntity(ImagemRequestDto requestDto) {
        return new Imagem(null, requestDto.getUrl());
    }

    public static ImagemResponseDto toResponseDto(Imagem imagem) {
        return new ImagemResponseDto(imagem.getIdImagem(), imagem.getUrl());
    }
}
