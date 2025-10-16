package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueBuscarPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueBuscarPorTipoUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

public class SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase {

    private final LoteItemEstoqueGateway gateway;

    private final ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarPorIdUseCase;

    public SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase(LoteItemEstoqueGateway gateway, ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarPorIdUseCase) {
        this.gateway = gateway;
        this.itemEstoqueBuscarPorIdUseCase = itemEstoqueBuscarPorIdUseCase;
    }

    public ItemEstoque execute(SaidaEstoque saidaEstoque, Double qtdAtualizar) {
        Integer idLoteItemEstoque = saidaEstoque.getLoteItemEstoque().getIdLoteItemEstoque();
        LoteItemEstoque loteItemEstoque = gateway.buscarPorId(idLoteItemEstoque);

        ItemEstoque itemEstoque = itemEstoqueBuscarPorIdUseCase.execute(loteItemEstoque.getItemEstoque().getIdItemEstoque());


        Double qtdEntradaNova = qtdAtualizar == 0.0
                ? saidaEstoque.getQtdSaida()
                : qtdAtualizar;

        itemEstoque.atualizarQuantidade(qtdEntradaNova);
        return itemEstoque;
    }

}
