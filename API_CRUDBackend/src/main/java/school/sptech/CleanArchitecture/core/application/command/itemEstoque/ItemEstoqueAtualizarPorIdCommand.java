package school.sptech.CleanArchitecture.core.application.command.itemEstoque;

import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueConfeccaoCommand;

import java.util.Set;

public record ItemEstoqueAtualizarPorIdCommand(
        Integer idItemEstoque,
        String descricao,
        String complemento,
        Double peso,
        Double qtdMinimo,
        Double qtdArmazenado,
        ItemEstoqueCategoriaCommand categoria,
        Set<ItemEstoqueCategoriaCommand> caracteristicas,
        ItemEstoquePrateleiraCommand prateleira,
        Set<ItemEstoqueConfeccaoCommand> confeccaoRoupa,
        Double preco,
        ItemEstoqueImagemCommand imagem
) {
}
