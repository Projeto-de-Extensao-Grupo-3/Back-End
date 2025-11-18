package school.sptech.CleanArchitecture.core.application.usecase.alerta;

import school.sptech.CleanArchitecture.core.adapters.AlertaGateway;
import school.sptech.CleanArchitecture.core.application.command.alerta.AlertaListarPorItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public class AlertaListarAlertasDoItemUseCase {

    private final AlertaGateway gateway;

    public AlertaListarAlertasDoItemUseCase(AlertaGateway gateway) {
        this.gateway = gateway;
    }

    public List<Alerta> execute(AlertaListarPorItemEstoqueCommand alertaItemEstoque){
        ItemEstoque itemEstoque = new ItemEstoque();
        itemEstoque.setIdItemEstoque(alertaItemEstoque.getIdItemEstoque());
        return gateway.findByItemEstoque(itemEstoque);
    }

}
