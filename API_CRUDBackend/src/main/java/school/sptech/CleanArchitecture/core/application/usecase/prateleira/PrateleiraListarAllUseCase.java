package school.sptech.CleanArchitecture.core.application.usecase.prateleira;

import school.sptech.CleanArchitecture.core.adapters.PrateleiraGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

import java.util.List;

public class PrateleiraListarAllUseCase {
    private final PrateleiraGateway gateway;

    public PrateleiraListarAllUseCase(PrateleiraGateway gateway) {
        this.gateway = gateway;
    }

    public List<Prateleira> executar(){
        return gateway.findAll();
    }

}
