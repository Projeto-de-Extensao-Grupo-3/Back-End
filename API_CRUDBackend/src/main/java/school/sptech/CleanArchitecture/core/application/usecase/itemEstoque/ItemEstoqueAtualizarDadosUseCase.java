package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;

public class ItemEstoqueAtualizarDadosUseCase {

    private final ItemEstoqueGateway gateway;

    private final ItemEstoqueBuscarPorIdUseCase buscarItemEstoquePorId;

    public ItemEstoqueAtualizarDadosUseCase(ItemEstoqueGateway gateway, ItemEstoqueBuscarPorIdUseCase buscarItemEstoquePorId) {
        this.gateway = gateway;
        this.buscarItemEstoquePorId = buscarItemEstoquePorId;
    }

    public ItemEstoque execute(LoteItemEstoque loteItemEstoque, Double qtdAtualizar){
        Integer idItemEstoque = loteItemEstoque.getItemEstoque().getIdItemEstoque();
        ItemEstoque itemEstoque = buscarItemEstoquePorId.execute(idItemEstoque);
        Double qtdEntradaNova = qtdAtualizar == 0
                ? loteItemEstoque.getQtdItem()
                : qtdAtualizar;
        itemEstoque.setQtdArmazenado(itemEstoque.getQtdArmazenado() + qtdEntradaNova);

        String descricaoItem = itemEstoque.getDescricao();
        if (descricaoItem != null && descricaoItem.toLowerCase().contains("tecido")){
            itemEstoque.setPreco(loteItemEstoque.getPreco());
        }

        return itemEstoque;
    }

}
