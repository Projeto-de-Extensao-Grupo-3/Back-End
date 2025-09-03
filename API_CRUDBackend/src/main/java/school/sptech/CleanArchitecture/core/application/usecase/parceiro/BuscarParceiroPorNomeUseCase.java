package school.sptech.CleanArchitecture.core.application.usecase.parceiro;

import school.sptech.CleanArchitecture.core.adapters.ParceiroGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

public class BuscarParceiroPorNomeUseCase {

    private final ParceiroGateway gateway;

    public BuscarParceiroPorNomeUseCase(ParceiroGateway gateway) {
        this.gateway = gateway;
    }

    public Parceiro executar(String nome){
        return gateway.findByNome(nome);
    }
}
