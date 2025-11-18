package school.sptech.CleanArchitecture.core.application.mapper;

import school.sptech.CleanArchitecture.core.application.command.categoria.CategoriaAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.command.categoria.CriarCategoriaCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;

public class CategoriaMapper {

    public static Categoria ofAtualizarCategoriaCommand(CategoriaAtualizarCommand command) {
        Categoria categoria = new Categoria();

        var categoriaPai = new Categoria();
        categoriaPai.setIdCategoria(command.categoriaPai().idCategoria());

        categoria.setIdCategoria(command.id());
        categoria.setNome(command.nome());
        categoria.setCategoriaPai(categoriaPai);

        return categoria;
    }

    public static Categoria ofCriarCategoriaCommand(CriarCategoriaCommand command) {
        Categoria categoriaPai = null;
        if (command.categoriaPai() != null) {
            categoriaPai = new Categoria();
            categoriaPai.setIdCategoria(command.categoriaPai().idCategoria());
        }

        Categoria categoria = new Categoria();
        categoria.setNome(command.nome());
        categoria.setCategoriaPai(categoriaPai);

        return categoria;
    }
}
