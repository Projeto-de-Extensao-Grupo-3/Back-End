package school.sptech.CleanArchitecture.core.application.usecase.prateleira;

import school.sptech.CleanArchitecture.core.adapters.PrateleiraGateway;
import school.sptech.CleanArchitecture.core.application.command.prateleira.PrateleiraAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.exception.Prateleira.PrateleiraNaoEncontradaException;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

public class PrateleiraAtualizarPorIdUseCase {

    private final PrateleiraGateway gateway;

    public PrateleiraAtualizarPorIdUseCase(PrateleiraGateway gateway) {
        this.gateway = gateway;
    }

    public Prateleira executar(PrateleiraAtualizarCommand command) {
        if(gateway.existsById(command.id())){
            var prateleiraParaAtualizar = new Prateleira();
            prateleiraParaAtualizar.setIdPrateleira(command.id());
            prateleiraParaAtualizar.setCodigo(command.codigo());
            return gateway.save(prateleiraParaAtualizar);
        }
        throw new PrateleiraNaoEncontradaException("Não foi possivel encontrar a prateleira com id: " + command.id());
    }

}
