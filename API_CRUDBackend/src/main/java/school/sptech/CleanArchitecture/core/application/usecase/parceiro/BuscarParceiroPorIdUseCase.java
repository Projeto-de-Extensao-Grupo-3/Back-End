package school.sptech.CleanArchitecture.core.application.usecase.parceiro;

import school.sptech.CleanArchitecture.core.adapters.ParceiroGateway;
import school.sptech.CleanArchitecture.core.application.exception.parceiro.ParceiroNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

import java.util.Optional;

public class BuscarParceiroPorIdUseCase {

    private final ParceiroGateway gateway;

    public BuscarParceiroPorIdUseCase(ParceiroGateway gateway) {
        this.gateway = gateway;
    }

    public Parceiro execute(Integer id){
        return gateway.findById(id)
                .orElseThrow(() -> new ParceiroNaoEncontradoException("parceiro nao encontrado"));
    }
}
