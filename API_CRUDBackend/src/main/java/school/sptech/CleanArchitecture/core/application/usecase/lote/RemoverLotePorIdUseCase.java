package school.sptech.CleanArchitecture.core.application.usecase.lote;

import school.sptech.CRUDBackend.exception.Lote.LoteNaoEncontradoException;
import school.sptech.CleanArchitecture.core.adapters.LoteGateway;

public class RemoverLotePorIdUseCase {

    private final LoteGateway gateway;

    public RemoverLotePorIdUseCase(LoteGateway gateway) {
        this.gateway = gateway;
    }

    public void executar(Integer id){
        if(gateway.existsById(id)){
            gateway.deleteById(id);
        }
        throw new LoteNaoEncontradoException("Este lote já existe no sistema");
    }
}
