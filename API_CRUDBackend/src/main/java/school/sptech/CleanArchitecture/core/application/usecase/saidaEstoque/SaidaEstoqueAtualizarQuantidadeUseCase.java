package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

public class SaidaEstoqueAtualizarQuantidadeUseCase {

    private final LoteItemEstoqueGateway gateway;

    public SaidaEstoqueAtualizarQuantidadeUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public ItemEstoque execute(SaidaEstoque saidaEstoque, Double qtdAtualizar) {
        Integer idLoteItemEstoque = saidaEstoque.getLoteItemEstoque().getIdLoteItemEstoque();
        LoteItemEstoque loteItemEstoque = gateway.buscarPorId(idLoteItemEstoque);

        ItemEstoque itemEstoque = loteItemEstoque.getItemEstoque();

        Double qtdEntradaNova = qtdAtualizar == 0.0
                ? saidaEstoque.getQtdSaida()
                : qtdAtualizar;

        itemEstoque.atualizarQuantidade(qtdEntradaNova);

        return itemEstoque;
    }

}
