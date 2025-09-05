package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CRUDBackend.exception.itensEstoque.ItemEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.mapper.ItemEstoqueMapper;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.ObserverItemEstoque;

public class ItemEstoqueAtualizarPorIdUseCase implements ObserverItemEstoque {

    private final ItemEstoqueGateway gateway;

    public ItemEstoqueAtualizarPorIdUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public ItemEstoque execute(ItemEstoqueAtualizarPorIdCommand command){
        if (gateway.existsById(command.idItemEstoque())) {
            ItemEstoque itemEstoqueParaAtualizar = ItemEstoqueMapper.ofAtualizarCommand(command);
            itemEstoqueParaAtualizar.setIdItemEstoque(command.idItemEstoque());
            return gateway.save(itemEstoqueParaAtualizar);
        }
        throw new ItemEstoqueNaoEncontradoException("O item para atualizar não existe");
    }

    @Override
    public void atualizarQuantidade(ItemEstoqueAtualizarPorIdCommand itemEstoque) {
        execute(itemEstoque);
    }

}
