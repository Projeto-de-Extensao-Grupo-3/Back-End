package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntityMapper;

public class ItemEstoqueAtualizarQuantidadeUseCase implements Observer {

    private final ItemEstoqueAtualizarPorIdUseCase atualizarPorIdUseCase;

    public ItemEstoqueAtualizarQuantidadeUseCase(ItemEstoqueAtualizarPorIdUseCase atualizarPorIdUseCase) {
        this.atualizarPorIdUseCase = atualizarPorIdUseCase;
    }

    @Override
    public void atualizarQuantidade(ItemEstoque itemEstoque){
        ItemEstoqueAtualizarPorIdCommand command = ItemEstoqueEntityMapper.toAtualizarPorIdCommand(itemEstoque);
        atualizarPorIdUseCase.execute(command);
    }

}
