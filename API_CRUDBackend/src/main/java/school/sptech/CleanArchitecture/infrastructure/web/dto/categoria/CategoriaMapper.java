package school.sptech.CleanArchitecture.infrastructure.web.dto.categoria;


import school.sptech.CleanArchitecture.core.application.command.categoria.CriarCategoriaCommand;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;

public class CategoriaMapper {

    public static CategoriaEntity toEntity(CriarCategoriaCommand command) {
        CategoriaEntity categoriaPai = null;
        if (command.categoriaPai() != null) {
            categoriaPai = new CategoriaEntity();
            categoriaPai.setIdCategoria(command.categoriaPai().idCategoria());
        }
        return new CategoriaEntity(
                null,
                command.nome(),
                categoriaPai
        );
    }

    public static CategoriaResponseDto toResponseDto(CategoriaEntity categoria) {
        return new CategoriaResponseDto(
                categoria.getIdCategoria(),
                categoria.getNome()
        );
    }
}
