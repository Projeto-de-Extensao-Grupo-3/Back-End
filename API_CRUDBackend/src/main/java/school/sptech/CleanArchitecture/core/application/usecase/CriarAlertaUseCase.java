package school.sptech.CleanArchitecture.core.application.usecase;

import school.sptech.CleanArchitecture.core.adapters.AlertaGateway;
import school.sptech.CleanArchitecture.core.application.command.alerta.AlertaCriacaoCommand;
import school.sptech.CleanArchitecture.core.application.command.alerta.AlertaMapper;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;

public class CriarAlertaUseCase {

    private final AlertaGateway gateway;

    public CriarAlertaUseCase(AlertaGateway alertaGateway) {
        this.gateway = alertaGateway;
    }

    public Alerta executar(AlertaCriacaoCommand command) {

        var alertaParaRegistrar = AlertaMapper.toEntity(command);

        return gateway.save(alertaParaRegistrar);
    }
}
