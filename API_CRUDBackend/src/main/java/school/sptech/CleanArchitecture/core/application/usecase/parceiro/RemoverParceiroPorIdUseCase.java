package school.sptech.CleanArchitecture.core.application.usecase.parceiro;

import school.sptech.CleanArchitecture.core.adapters.ParceiroGateway;
import school.sptech.CleanArchitecture.core.application.exception.Prateleira.PrateleiraNaoEncontradaException;

public class RemoverParceiroPorIdUseCase {

    private final ParceiroGateway gateway;

    public RemoverParceiroPorIdUseCase(ParceiroGateway gateway) {
        this.gateway = gateway;
    }

    public void executar(Integer id){
        if(gateway.existsById(id)){
            gateway.deleteById(id);
        }else{
            throw new PrateleiraNaoEncontradaException("Prateleira foi possivel encontrar prateleira com id: " + id);
        }
    }
}
