package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;

public class RemoverLoteItemEstoqueUseCase {

    private final LoteItemEstoqueGateway gateway;

    public RemoverLoteItemEstoqueUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public void executar(Integer id){
        if(gateway.existsById(id)){
            gateway.deleteById(id);
            return;
        }
        throw new RuntimeException("Este lote de item de estoque não existe no sistema");
    }
}
