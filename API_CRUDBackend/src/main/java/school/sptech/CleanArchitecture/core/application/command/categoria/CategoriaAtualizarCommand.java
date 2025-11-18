package school.sptech.CleanArchitecture.core.application.command.categoria;

public record CategoriaAtualizarCommand(
        Integer id,
        String nome,
        CategoriaPaiCommand categoriaPai
) {
}
