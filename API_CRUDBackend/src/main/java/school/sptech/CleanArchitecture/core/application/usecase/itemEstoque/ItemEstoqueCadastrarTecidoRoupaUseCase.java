package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueCadastrarTecidoRoupaCommand;
import school.sptech.CleanArchitecture.core.application.exceptions.itensEstoque.ItemEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public class ItemEstoqueCadastrarTecidoRoupaUseCase {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueCadastrarTecidoRoupaUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public ItemEstoque execute(ItemEstoqueCadastrarTecidoRoupaCommand command){
        if (gateway.existsById(command.idRoupa())) {
            ItemEstoque roupa = gateway.findById(command.idRoupa()).get();
            roupa.setConfeccaoRoupa(command.tecidos());
            return gateway.save(roupa);
        }
        throw new ItemEstoqueNaoEncontradoException("A roupa não existe no estoque");
    }
}
