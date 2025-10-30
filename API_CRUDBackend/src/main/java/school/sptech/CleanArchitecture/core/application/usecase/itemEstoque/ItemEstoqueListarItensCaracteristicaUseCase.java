package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public class ItemEstoqueListarItensCaracteristicaUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueListarItensCaracteristicaUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<ItemEstoque> execute(Integer idCaracteristica){
        return gateway.findByCaracteristicas_IdCategoria(idCaracteristica);
    }

}
