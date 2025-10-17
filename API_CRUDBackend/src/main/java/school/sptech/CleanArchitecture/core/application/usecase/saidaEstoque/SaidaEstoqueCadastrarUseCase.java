package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.SaidaEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.mapper.SaidaEstoqueMapper;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.BuscarFuncionarioPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque.BuscarPorIdLoteItemEstoqueUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.parceiro.BuscarParceiroPorIdUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

public class SaidaEstoqueCadastrarUseCase {

    private final SaidaEstoqueGateway saidaGateway;

    private final BuscarFuncionarioPorIdUseCase funcionarioPorIdUseCase;

    private final BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase;

    private final BuscarParceiroPorIdUseCase parceiroPorIdUseCase;

    private final SaidaEstoqueEnviarEmailENotificarObservers enviarEmailENotificarObservers;

    public SaidaEstoqueCadastrarUseCase(SaidaEstoqueGateway saidaGateway, BuscarFuncionarioPorIdUseCase funcionarioPorIdUseCase,
                                        BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase, BuscarParceiroPorIdUseCase parceiroPorIdUseCase, SaidaEstoqueEnviarEmailENotificarObservers enviarEmailENotificarObservers) {
        this.saidaGateway = saidaGateway;
        this.funcionarioPorIdUseCase = funcionarioPorIdUseCase;
        this.loteItemEstoqueUseCase = loteItemEstoqueUseCase;
        this.parceiroPorIdUseCase = parceiroPorIdUseCase;
        this.enviarEmailENotificarObservers = enviarEmailENotificarObservers;
    }

    public SaidaEstoque execute(SaidaEstoqueCadastrarCommand command){
        SaidaEstoque saidaDeEstoque = SaidaEstoqueMapper.ofCadastrarCommand(command);

        saidaDeEstoque.setResponsavel(
                funcionarioPorIdUseCase.execute(saidaDeEstoque.getResponsavel().getIdFuncionario()));
        saidaDeEstoque.setCostureira(
                parceiroPorIdUseCase.execute(saidaDeEstoque.getCostureira().getId()));
        saidaDeEstoque.setLoteItemEstoque(
                loteItemEstoqueUseCase.executar(saidaDeEstoque.getLoteItemEstoque().getIdLoteItemEstoque()));

        enviarEmailENotificarObservers.execute(saidaDeEstoque);

        return saidaGateway.save(saidaDeEstoque);
    }

}
