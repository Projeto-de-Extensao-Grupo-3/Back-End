package school.sptech.CRUDBackend.dto.Categoria;

import school.sptech.CRUDBackend.entity.Categoria;

public class CategoriaMapper {

    public static Categoria toEntity(CategoriaRequestDto requestDto) {
        return new Categoria(
                null,
                requestDto.getNome()
        );
    }

    public static CategoriaResponseDto toResponseDto(Categoria categoria) {
        return new CategoriaResponseDto(
                categoria.getId(),
                categoria.getNome()
        );
    }
}
