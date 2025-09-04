package school.sptech.CleanArchitecture.core.application.mapper;

import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.domain.entity.*;

import java.util.Set;
import java.util.stream.Collectors;

public class ItemEstoqueMapper {


    public static ItemEstoque ofCadastrarItemCommand(ItemEstoqueCadastrarCommand command) {
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

        Set<ConfeccaoRoupa> confeccaoRoupas = command.confeccaoRoupa()
                .stream().map(
                        categoriaCommand -> {
                            ConfeccaoRoupa confeccao = new ConfeccaoRoupa();
                            confeccao.setIdConfeccaoRoupa(categoriaCommand.idConfeccaoRoupa());
                            return confeccao;
                        }
                ).collect(Collectors.toSet());

        itemEstoque.setIdItemEstoque(command.idItemEstoque());
        itemEstoque.setDescricao(command.descricao());
        itemEstoque.setComplemento(command.complemento());
        itemEstoque.setPeso(command.peso());
        itemEstoque.setQtdMinimo(command.qtdMinimo());
        itemEstoque.setQtdArmazenado(command.qtdArmazenado());
        itemEstoque.setCategoria(categoria);
        itemEstoque.setCaracteristicas(caracteristicas);
        itemEstoque.setPrateleira(prateleira);
        itemEstoque.setConfeccaoRoupa(confeccaoRoupas);
        itemEstoque.setPreco(command.preco());
        itemEstoque.setImagem(imagem);
        return itemEstoque;
    }
}
