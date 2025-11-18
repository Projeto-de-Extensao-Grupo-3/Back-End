package school.sptech.CleanArchitecture.core.application.usecase.parceiro;

import school.sptech.CleanArchitecture.core.adapters.ParceiroGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

import java.util.List;

public class BuscarParceiroPorNomeUseCase {

    private final ParceiroGateway gateway;

    public BuscarParceiroPorNomeUseCase(ParceiroGateway gateway) {
        this.gateway = gateway;
    }

    public List<Parceiro> executar(String categoria, String nome){
        return gateway.findByCategoriaAndNomeContainsIgnoreCase(categoria, nome);
    }
}
