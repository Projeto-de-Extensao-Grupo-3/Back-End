package school.sptech.CleanArchitecture.core.application.command.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public class ItemEstoqueBuscarPorDescricaoUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueBuscarPorDescricaoUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<ItemEstoque> execute(String descricao, String tipo){
        return gateway.findByDescricaoContainsIgnoreCase(descricao, tipo);
    }
}
