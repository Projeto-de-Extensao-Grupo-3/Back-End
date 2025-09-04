package school.sptech.CleanArchitecture.core.application.usecase.prateleira;

import school.sptech.CRUDBackend.exception.Prateleira.PrateleiraNaoEncontradaException;
import school.sptech.CleanArchitecture.core.adapters.PrateleiraGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

public class PrateleiraBuscarPorCodigoUseCase {

    private final PrateleiraGateway gateway;

    public PrateleiraBuscarPorCodigoUseCase(PrateleiraGateway gateway) {
        this.gateway = gateway;
    }

    public Prateleira executar(String codigo){
        return gateway.findByCodigo(codigo);
    }

}
