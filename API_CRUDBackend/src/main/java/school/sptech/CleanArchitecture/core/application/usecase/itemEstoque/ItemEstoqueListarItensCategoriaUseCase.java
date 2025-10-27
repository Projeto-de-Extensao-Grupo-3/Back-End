package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public class ItemEstoqueListarItensCategoriaUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueListarItensCategoriaUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<ItemEstoque> execute(Integer id){
        return gateway.findByCategoria(new Categoria(id));
    }
}
