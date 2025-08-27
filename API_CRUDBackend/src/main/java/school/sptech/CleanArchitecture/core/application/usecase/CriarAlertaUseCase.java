package school.sptech.CleanArchitecture.core.application.usecase;

import school.sptech.CleanArchitecture.core.adapters.AlertaGateway;
import school.sptech.CleanArchitecture.core.application.dto.alerta.AlertaCriacaoDto;
import school.sptech.CleanArchitecture.core.application.dto.alerta.AlertaMapper;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;

public class CriarAlertaUseCase {

    private final AlertaGateway gateway;

    public CriarAlertaUseCase(AlertaGateway alertaGateway) {
        this.gateway = alertaGateway;
    }

    public Alerta executar(AlertaCriacaoDto command) {

        var alertaParaRegistrar = AlertaMapper.toEntity(command);

        return gateway.save(alertaParaRegistrar);
    }

}
