package school.sptech.CleanArchitecture.core.application.usecase.alerta;

import school.sptech.CleanArchitecture.core.adapters.AlertaGateway;
import school.sptech.CleanArchitecture.core.application.command.alerta.CriarAlertaCommand;
import school.sptech.CleanArchitecture.core.application.mapper.AlertaMapper;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;

public class CriarAlertaUseCase {

    private final AlertaGateway gateway;

    public CriarAlertaUseCase(AlertaGateway alertaGateway) {
        this.gateway = alertaGateway;
    }

    public Alerta executar(CriarAlertaCommand command) {

        var alertaParaRegistrar = AlertaMapper.ofCriarCommand(command);
        System.out.println("classe convertida de command " + alertaParaRegistrar.getItemEstoque());
        return gateway.save(alertaParaRegistrar);
    }

}
