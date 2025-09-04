package school.sptech.CleanArchitecture.core.application.command.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public class ItemEstoqueBuscarPorTipoUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueBuscarPorTipoUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<ItemEstoque> execute(String tipo){
        return gateway.findByTipo(tipo);
    }
}
