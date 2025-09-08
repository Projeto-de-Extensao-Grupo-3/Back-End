package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.CriarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;

public class CadastrarLoteItemEstoqueUseCase {

    private final LoteItemEstoqueGateway gateway;

    public CadastrarLoteItemEstoqueUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public LoteItemEstoque executar(CriarLoteItemEstoqueCommand command){
        LoteItemEstoque loteItemEstoque = new LoteItemEstoque(
                command.qtdItem(),
                command.preco(),
                command.itemEstoque(),
                command.lote()
        );
        return gateway.save(loteItemEstoque);
    }
}
