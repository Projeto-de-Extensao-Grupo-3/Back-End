package school.sptech.CleanArchitecture.core.application.usecase.prateleira;

import school.sptech.CleanArchitecture.core.adapters.PrateleiraGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.Prateleira.PrateleiraNaoEncontradaException;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

public class PrateleiraRemoverPorIdUseCase {

    private final PrateleiraGateway gateway;

    public PrateleiraRemoverPorIdUseCase(PrateleiraGateway gateway) {
        this.gateway = gateway;
    }

    public void executar(Integer id){
        if(gateway.existsById(id)){
            gateway.deleteById(id);
        } else {
            throw new PrateleiraNaoEncontradaException("Não foi possivel encontrar prateleira com id: " + id);
        }
    }
}
