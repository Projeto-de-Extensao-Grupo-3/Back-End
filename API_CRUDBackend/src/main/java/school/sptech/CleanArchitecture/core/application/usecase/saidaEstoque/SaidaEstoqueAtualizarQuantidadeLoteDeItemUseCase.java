package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

public class SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase {

    private final LoteItemEstoqueGateway gateway;

    public SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public ItemEstoque execute(SaidaEstoque saidaEstoque, Double qtdAtualizar) {
        Integer idLoteItemEstoque = saidaEstoque.getLoteItemEstoque().getIdLoteItemEstoque();
        LoteItemEstoque loteItemEstoque = gateway.buscarPorId(idLoteItemEstoque);

        ItemEstoque itemEstoque = new ItemEstoque();
        itemEstoque.setIdItemEstoque(loteItemEstoque.getItemEstoque());

        Double qtdEntradaNova = saidaEstoque.getQtdSaida() == 0.0
                ? saidaEstoque.getQtdSaida()
                : qtdAtualizar;

        itemEstoque.atualizarQuantidade(qtdEntradaNova);
        return itemEstoque;
    }

}
