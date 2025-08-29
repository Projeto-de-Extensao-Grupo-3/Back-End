package school.sptech.CleanArchitecture.core.application.usecase.alerta;

import school.sptech.CleanArchitecture.core.adapters.AlertaGateway;
import school.sptech.CleanArchitecture.core.application.command.alerta.CriarAlertaCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;

public class CriarAlertaUseCase {

    private final AlertaGateway gateway;

    public CriarAlertaUseCase(AlertaGateway alertaGateway) {
        this.gateway = alertaGateway;
    }

    public Alerta executar(CriarAlertaCommand command) {

        var alertaParaRegistrar = new Alerta();
        alertaParaRegistrar.setDescricao(command.getDescricao());
        alertaParaRegistrar.setItemEstoque(command.getItemEstoque());
        alertaParaRegistrar.setDataHora(command.getDataHora());

        return gateway.save(alertaParaRegistrar);
    }

}
