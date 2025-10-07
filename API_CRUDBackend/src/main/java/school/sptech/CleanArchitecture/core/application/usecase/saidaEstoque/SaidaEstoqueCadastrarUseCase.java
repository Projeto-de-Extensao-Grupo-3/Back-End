package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.SaidaEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.mapper.SaidaEstoqueMapper;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.BuscarFuncionarioPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque.BuscarPorIdLoteItemEstoqueUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.parceiro.BuscarParceiroPorIdUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;
import school.sptech.CleanArchitecture.core.domain.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class SaidaEstoqueCadastrarUseCase implements Subject {

    private final SaidaEstoqueGateway saidaGateway;

    private final SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarSaidaUseCase;

    private final BuscarFuncionarioPorIdUseCase funcionarioPorIdUseCase;

    private final BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase;

    private final BuscarParceiroPorIdUseCase parceiroPorIdUseCase;

    private final List<Observer> observadores = new ArrayList<>();

    public SaidaEstoqueCadastrarUseCase(SaidaEstoqueGateway saidaGateway, SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarSaidaUseCase, BuscarFuncionarioPorIdUseCase funcionarioPorIdUseCase, BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase, BuscarParceiroPorIdUseCase parceiroPorIdUseCase) {
        this.saidaGateway = saidaGateway;
        this.atualizarSaidaUseCase = atualizarSaidaUseCase;
        this.funcionarioPorIdUseCase = funcionarioPorIdUseCase;
        this.loteItemEstoqueUseCase = loteItemEstoqueUseCase;
        this.parceiroPorIdUseCase = parceiroPorIdUseCase;
    }

    public SaidaEstoque execute(SaidaEstoqueCadastrarCommand command){
        SaidaEstoque saidaDeEstoque = SaidaEstoqueMapper.ofCadastrarCommand(command);
        ItemEstoque itemEstoqueAtualizado = atualizarSaidaUseCase.execute(saidaDeEstoque, 0.0);
        notificarObservers(itemEstoqueAtualizado);

        saidaDeEstoque.setResponsavel(funcionarioPorIdUseCase.execute(saidaDeEstoque.getResponsavel().getIdFuncionario()));
        saidaDeEstoque.setCostureira(parceiroPorIdUseCase.execute(saidaDeEstoque.getCostureira().getId()));
        saidaDeEstoque.setLoteItemEstoque(loteItemEstoqueUseCase.executar(saidaDeEstoque.getLoteItemEstoque().getIdLoteItemEstoque()));

        return saidaGateway.save(saidaDeEstoque);
    }

    @Override
    public void adicionarObservador(Observer observador) {
            observadores.add(observador);
    }

    @Override
    public void removerObservador(Observer observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservers(ItemEstoque itemEstoque) {
        for (Observer observador : observadores) {
            observador.atualizarQuantidade(itemEstoque);
        }
    }
}
