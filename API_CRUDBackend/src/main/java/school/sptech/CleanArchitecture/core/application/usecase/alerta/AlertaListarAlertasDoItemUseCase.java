package school.sptech.CleanArchitecture.core.application.usecase.alerta;

import school.sptech.CleanArchitecture.core.adapters.AlertaGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;

import java.util.List;

public class AlertaListarAlertasDoItemUseCase {

    private final AlertaGateway gateway;

    public AlertaListarAlertasDoItemUseCase(AlertaGateway gateway) {
        this.gateway = gateway;
    }

    public List<Alerta> execute(ItemEstoqueEntity itemEstoque){
        return gateway.findByItemEstoque(ItemEstoqueEntityMa);
    }

}
