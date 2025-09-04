package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public class ItemEstoqueListAllUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueListAllUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<ItemEstoque> execute(){
        return gateway.findAll();
    }

}
