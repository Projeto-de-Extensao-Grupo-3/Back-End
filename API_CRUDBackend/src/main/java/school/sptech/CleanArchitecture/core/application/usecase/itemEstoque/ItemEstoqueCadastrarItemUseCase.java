package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CRUDBackend.exception.itensEstoque.ItemEstoqueConflitoException;
import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public class ItemEstoqueCadastrarItemUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueCadastrarItemUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

//    public ItemEstoque execute(ItemEstoqueCadastrarCommand command){
//        if(gateway.existsByDescricao(command.descricao())) {
//            throw new ItemEstoqueConflitoException("Este item já existe no sistema.");
//        }
//        ItemEstoque itemEstoque = new ItemEstoque(command);
//        return gateway.save(command);
//    }

}
