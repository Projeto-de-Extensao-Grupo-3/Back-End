package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.AtualizarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.application.exceptions.LoteItemEstoque.LoteItemEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.LoteItemEstoqueMapper;

public class AtualizarPorIdloteItemEstoqueUseCase {

    private final LoteItemEstoqueGateway gateway;

    public AtualizarPorIdloteItemEstoqueUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public LoteItemEstoque executar(AtualizarLoteItemEstoqueCommand command) {
        if(gateway.existsById(command.idLoteItemEstoque())) {
            LoteItemEstoque loteItemEstoqueParaAtualizar = LoteItemEstoqueMapper.ofAtualizarCommand(command);

            return gateway.save(loteItemEstoqueParaAtualizar);
        }
        throw new LoteItemEstoqueNaoEncontradoException("Lote Item Estoque não encontrado");
    }
}
