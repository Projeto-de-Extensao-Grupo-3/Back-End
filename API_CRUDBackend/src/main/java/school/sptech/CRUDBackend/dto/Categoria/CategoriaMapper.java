package school.sptech.CRUDBackend.dto.Categoria;

import school.sptech.CRUDBackend.entity.Categoria;

public class CategoriaMapper {

    public static Categoria toEntity(CategoriaRequestDto requestDto) {
        Categoria categoriaPai = null;
        if (requestDto.getCategoriaPai() != null) {
            categoriaPai = new Categoria();
            categoriaPai.setIdCategoria(requestDto.getCategoriaPai().getIdCategoria());
        }
        return new Categoria(
                null,
                requestDto.getNome(),
                categoriaPai
        );
    }

    public static CategoriaResponseDto toResponseDto(Categoria categoria) {
        return new CategoriaResponseDto(
                categoria.getIdCategoria(),
                categoria.getNome()
        );
    }
}
