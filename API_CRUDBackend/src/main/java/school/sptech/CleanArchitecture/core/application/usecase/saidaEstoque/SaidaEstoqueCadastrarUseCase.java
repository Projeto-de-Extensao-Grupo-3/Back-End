package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.SaidaEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.mapper.SaidaEstoqueMapper;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.BuscarFuncionarioPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueAtualizarPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueBuscarPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque.BuscarPorIdLoteItemEstoqueUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.parceiro.BuscarParceiroPorIdUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntityMapper;

public class SaidaEstoqueCadastrarUseCase {

    private final SaidaEstoqueGateway saidaGateway;

    private final ItemEstoqueAtualizarPorIdUseCase atualizarPorIdUseCase;

    private final ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarUseCase;

    private final BuscarFuncionarioPorIdUseCase funcionarioPorIdUseCase;

    private final BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase;

    private final BuscarParceiroPorIdUseCase parceiroPorIdUseCase;

    private final SaidaEstoqueEnviarEmailENotificarObservers enviarEmailENotificarObservers;

    public SaidaEstoqueCadastrarUseCase(SaidaEstoqueGateway saidaGateway, ItemEstoqueAtualizarPorIdUseCase atualizarPorIdUseCase, ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarUseCase, BuscarFuncionarioPorIdUseCase funcionarioPorIdUseCase, BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase, BuscarParceiroPorIdUseCase parceiroPorIdUseCase, SaidaEstoqueEnviarEmailENotificarObservers enviarEmailENotificarObservers) {
        this.saidaGateway = saidaGateway;
        this.atualizarPorIdUseCase = atualizarPorIdUseCase;
        this.itemEstoqueBuscarUseCase = itemEstoqueBuscarUseCase;
        this.funcionarioPorIdUseCase = funcionarioPorIdUseCase;
        this.loteItemEstoqueUseCase = loteItemEstoqueUseCase;
        this.parceiroPorIdUseCase = parceiroPorIdUseCase;
        this.enviarEmailENotificarObservers = enviarEmailENotificarObservers;
    }

    public SaidaEstoque execute(SaidaEstoqueCadastrarCommand command){
        SaidaEstoque saidaDeEstoque = SaidaEstoqueMapper.ofCadastrarCommand(command);

        // Atualização da quantidade de ItemEstoque
        LoteItemEstoque loteItemEstoque = loteItemEstoqueUseCase.executar(saidaDeEstoque.getLoteItemEstoque().getIdLoteItemEstoque());
        ItemEstoque itemEstoque = itemEstoqueBuscarUseCase.execute(loteItemEstoque.getItemEstoque().getIdItemEstoque());
        Double qtdEntradaNova = saidaDeEstoque.getQtdSaida();
        itemEstoque.setQtdArmazenado(itemEstoque.getQtdArmazenado() - qtdEntradaNova);

        ItemEstoqueAtualizarPorIdCommand commandAtualizarItemEstoque = ItemEstoqueEntityMapper.toAtualizarPorIdCommand(itemEstoque);
        atualizarPorIdUseCase.execute(commandAtualizarItemEstoque);

        /*--------------------------------------------------------------------------------------*/

        saidaDeEstoque.setResponsavel(
                funcionarioPorIdUseCase.execute(saidaDeEstoque.getResponsavel().getIdFuncionario()));
        saidaDeEstoque.setCostureira(saidaDeEstoque.getCostureira() == null ? null :
                parceiroPorIdUseCase.execute(saidaDeEstoque.getCostureira().getId()));
        saidaDeEstoque.setLoteItemEstoque(loteItemEstoque);

        enviarEmailENotificarObservers.execute(saidaDeEstoque);

        return saidaGateway.save(saidaDeEstoque);
    }

}
