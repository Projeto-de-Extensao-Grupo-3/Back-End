package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.SaidaEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.mapper.SaidaEstoqueMapper;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;
import school.sptech.CleanArchitecture.core.domain.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class SaidaEstoqueCadastrarUseCase implements Subject {

    private final SaidaEstoqueGateway saidaGateway;

    private final SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarSaidaUseCase;

    private final List<Observer> observadores = new ArrayList<>();

    public SaidaEstoqueCadastrarUseCase(SaidaEstoqueGateway saidaGateway, SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarSaidaUseCase) {
        this.saidaGateway = saidaGateway;
        this.atualizarSaidaUseCase = atualizarSaidaUseCase;
    }

    public SaidaEstoque execute(SaidaEstoqueCadastrarCommand command){
        SaidaEstoque saidaDeEstoque = SaidaEstoqueMapper.ofCadastrarCommand(command);
        Double qtdParaAtualizar = command.qtdAtualizar();
        ItemEstoque itemEstoqueAtualizado = atualizarSaidaUseCase.execute(saidaDeEstoque, qtdParaAtualizar);
        notificarObservers(itemEstoqueAtualizado);
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
    public void notificarObservers(school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque itemEstoque) {
        for (Observer observador : observadores) {
            observador.atualizarQuantidade(itemEstoque);
        }
    }
}
