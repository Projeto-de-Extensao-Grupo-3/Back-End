package school.sptech.CleanArchitecture.core.application.usecase.prateleira;

import school.sptech.CleanArchitecture.core.adapters.PrateleiraGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

public class PrateleiraBuscarPorIdUseCase {

    private final PrateleiraGateway gateway;

    public PrateleiraBuscarPorIdUseCase(PrateleiraGateway gateway) {
        this.gateway = gateway;
    }

    public Prateleira executar(Integer id){
        return gateway.findById(id);
    }

}
