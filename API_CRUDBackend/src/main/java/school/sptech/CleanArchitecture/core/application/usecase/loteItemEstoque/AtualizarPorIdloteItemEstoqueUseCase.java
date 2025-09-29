package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.AtualizarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.application.exception.LoteItemEstoque.LoteItemEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;

public class AtualizarPorIdloteItemEstoqueUseCase {

    private final LoteItemEstoqueGateway gateway;

    public AtualizarPorIdloteItemEstoqueUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public LoteItemEstoque executar(AtualizarLoteItemEstoqueCommand command) {
        if(gateway.existsById(command.idLoteItemEstoque())) {
            var loteItemEstoqueParaAtualizar = new LoteItemEstoque();
            loteItemEstoqueParaAtualizar.setIdLoteItemEstoque(command.idLoteItemEstoque());
            loteItemEstoqueParaAtualizar.setQtdItem(command.qtdItem());
            loteItemEstoqueParaAtualizar.setPreco(command.preco());
            loteItemEstoqueParaAtualizar.setItemEstoque(command.itemEstoque().getIdItemEstoque());
            loteItemEstoqueParaAtualizar.setLote(command.lote().getIdLote());
            return gateway.save(loteItemEstoqueParaAtualizar);
        }
        throw new LoteItemEstoqueNaoEncontradoException("Lote Item Estoque não encontrado");
    }
}
