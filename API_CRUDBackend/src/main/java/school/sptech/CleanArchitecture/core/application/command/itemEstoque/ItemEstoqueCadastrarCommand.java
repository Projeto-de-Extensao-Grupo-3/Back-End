package school.sptech.CleanArchitecture.core.application.command.itemEstoque;

import java.util.Set;

public record ItemEstoqueCadastrarCommand(
    String descricao,
    String complemento,
    Double peso,
    Double qtdMinimo,
    Double qtdArmazenado,
    ItemEstoqueCategoriaCommand categoria,
    Set<ItemEstoqueCategoriaCommand> caracteristicas,
    ItemEstoquePrateleiraCommand plateleira,
    Double preco,
    ItemEstoqueImagemCommand imagem
) {
}
