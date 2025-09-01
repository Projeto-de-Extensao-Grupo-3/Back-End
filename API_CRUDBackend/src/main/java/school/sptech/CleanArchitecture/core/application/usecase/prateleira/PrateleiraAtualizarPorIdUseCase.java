package school.sptech.CleanArchitecture.core.application.usecase.prateleira;

import school.sptech.CleanArchitecture.core.adapters.PrateleiraGateway;
import school.sptech.CleanArchitecture.core.application.command.prateleira.CriarPrateleiraCommand;
import school.sptech.CleanArchitecture.core.application.exception.Prateleira.PrateleiraNaoEncontradaException;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

public class PrateleiraAtualizarPorIdUseCase {

    private final PrateleiraGateway gateway;

    public PrateleiraAtualizarPorIdUseCase(PrateleiraGateway gateway) {
        this.gateway = gateway;
    }

    public Prateleira executar(Integer id, CriarPrateleiraCommand command){
        if(gateway.existsById(id)){
            var prateleiraParaAtualizar = new Prateleira();
            prateleiraParaAtualizar.setIdPrateleira(id);
            prateleiraParaAtualizar.setCodigo(command.codigo());
            return gateway.save(prateleiraParaAtualizar);
        }
        throw new PrateleiraNaoEncontradaException("Não foi possivel encontrar a prateleira com id: " + id);
    }

}
