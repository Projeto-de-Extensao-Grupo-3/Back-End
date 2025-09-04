package school.sptech.CleanArchitecture.core.application.usecase.prateleira;


import school.sptech.CleanArchitecture.core.adapters.PrateleiraGateway;
import school.sptech.CleanArchitecture.core.application.command.prateleira.CriarPrateleiraCommand;
import school.sptech.CleanArchitecture.core.application.exception.Prateleira.PrateleiraConflitoException;
import school.sptech.CleanArchitecture.core.application.mapper.PrateleiraMapper;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

public class CriarPrateleiraUseCase {

    private final PrateleiraGateway gateway;

    public CriarPrateleiraUseCase(PrateleiraGateway gateway) {
        this.gateway = gateway;
    }

    public Prateleira executar(CriarPrateleiraCommand command){

        if(gateway.existsByCodigo(command.codigo())){
            throw new PrateleiraConflitoException("Essa estante já existe");
        }
        var prateleiraParaRegistrar = PrateleiraMapper.ofCriarPrateleiraCommand(command);
        return gateway.save(prateleiraParaRegistrar);
    }

}
