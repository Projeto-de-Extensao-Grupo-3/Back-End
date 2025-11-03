package school.sptech.CleanArchitecture.core.application.mapper;

import school.sptech.CleanArchitecture.core.application.command.itemEstoque.*;
import school.sptech.CleanArchitecture.core.domain.entity.*;

import java.util.Set;
import java.util.stream.Collectors;

public class ItemEstoqueMapper {


    public static ItemEstoque ofCadastrarItemCommand(ItemEstoqueCadastrarCommand command) {
        ItemEstoque itemEstoque = new ItemEstoque();

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(command.categoria().idCategoria());
        System.out.println("Metodo ofCadastrarItemCommand: " + command.categoria().idCategoria());

        Set<Categoria> caracteristicas = command.caracteristicas()
                .stream().map(
                categoriaCommand -> {
                    Categoria caracteristica = new Categoria();
                    caracteristica.setIdCategoria(categoriaCommand.idCategoria());
                    return caracteristica;
                }
        ).collect(Collectors.toSet());

        Prateleira prateleira = new Prateleira();
        prateleira.setIdPrateleira(command.plateleira().idPrateleira());

        Imagem imagem = new Imagem();
        imagem.setIdImagem(command.imagem().idImagem());
        imagem.setUrl(command.imagem().url());

        itemEstoque.setIdItemEstoque(null);
        itemEstoque.setDescricao(command.descricao());
        itemEstoque.setComplemento(command.complemento());
        itemEstoque.setPeso(command.peso());
        itemEstoque.setQtdMinimo(command.qtdMinimo());
        itemEstoque.setQtdArmazenado(command.qtdArmazenado());
        itemEstoque.setNotificar(command.notificar());
        itemEstoque.setCategoria(categoria);
        itemEstoque.setCaracteristicas(caracteristicas);
        itemEstoque.setPrateleira(prateleira);
        itemEstoque.setPreco(command.preco());
        itemEstoque.setImagem(imagem);
        return itemEstoque;
    }

    public static ItemEstoque ofAtualizarCommand(ItemEstoqueAtualizarPorIdCommand command) {
        ItemEstoque itemEstoque = new ItemEstoque();

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(command.categoria().idCategoria());

        Set<Categoria> caracteristicas = command.caracteristicas()
                .stream().map(
                        categoriaCommand -> {
                            Categoria caracteristica = new Categoria();
                            caracteristica.setIdCategoria(categoriaCommand.idCategoria());
                            return caracteristica;
                        }
                ).collect(Collectors.toSet());

        Prateleira prateleira = new Prateleira();
        prateleira.setIdPrateleira(command.prateleira().idPrateleira());

        Imagem imagem = new Imagem();
        imagem.setIdImagem(command.imagem().idImagem());
        imagem.setUrl(command.imagem().url());

        itemEstoque.setIdItemEstoque(command.idItemEstoque());
        itemEstoque.setDescricao(command.descricao());
        itemEstoque.setComplemento(command.complemento());
        itemEstoque.setPeso(command.peso());
        itemEstoque.setQtdMinimo(command.qtdMinimo());
        itemEstoque.setQtdArmazenado(command.qtdArmazenado());
        itemEstoque.setNotificar(command.notificar());
        itemEstoque.setCategoria(categoria);
        itemEstoque.setCaracteristicas(caracteristicas);
        itemEstoque.setPrateleira(prateleira);
        itemEstoque.setConfeccaoRoupa(null);
        itemEstoque.setPreco(command.preco());
        itemEstoque.setImagem(imagem);
        return itemEstoque;
    }

    public static ItemEstoqueAtualizarPorIdCommand toAtualizarCommand(ItemEstoque itemEstoque) {

        ItemEstoqueCategoriaCommand categoria = new ItemEstoqueCategoriaCommand(
                itemEstoque.getCategoria().getIdCategoria());

        Set<ItemEstoqueCategoriaCommand> caracteristicas = itemEstoque.getCaracteristicas()
                .stream().map(
                        caracteristica -> {
                            ItemEstoqueCategoriaCommand caracteristicaCommand = new ItemEstoqueCategoriaCommand(caracteristica.getIdCategoria());
                            return caracteristicaCommand;
                        }
                ).collect(Collectors.toSet());

        ItemEstoquePrateleiraCommand prateleira = new ItemEstoquePrateleiraCommand(
                itemEstoque.getPrateleira().getIdPrateleira()
        );

        ItemEstoqueImagemCommand imagem = new ItemEstoqueImagemCommand(
                itemEstoque.getImagem().getIdImagem(),
                itemEstoque.getImagem().getUrl()
        );

        ItemEstoqueAtualizarPorIdCommand itemCommand = new ItemEstoqueAtualizarPorIdCommand(
                itemEstoque.getIdItemEstoque(),
            itemEstoque.getDescricao(),
            itemEstoque.getComplemento(),
            itemEstoque.getPeso(),
                itemEstoque.getQtdMinimo(),
                itemEstoque.getQtdArmazenado(),
                itemEstoque.getNotificar(),
                categoria,
                caracteristicas,
                prateleira,
                itemEstoque.getPreco(),
                imagem
        );

        return itemCommand;
    }

}
