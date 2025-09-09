package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.SaidaEstoqueAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.exception.SaidaEstoque.SaidaEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.application.mapper.SaidaEstoqueMapper;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;
import school.sptech.CleanArchitecture.core.domain.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class SaidaEstoqueAtualizarPorIdUseCase implements Subject {

    private final SaidaEstoqueGateway gateway;

    private final SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarQuantidadeUseCase;

    private final List<Observer> observadores = new ArrayList<>();

    public SaidaEstoqueAtualizarPorIdUseCase(SaidaEstoqueGateway gateway, SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarQuantidadeUseCase) {
        this.gateway = gateway;
        this.atualizarQuantidadeUseCase = atualizarQuantidadeUseCase;
    }

    public SaidaEstoque execute(SaidaEstoqueAtualizarPorIdCommand command){
        Integer idSaida = command.idSaidaEstoque();
        if (gateway.existsById(idSaida)){
            SaidaEstoque saidaParaAtualizar = SaidaEstoqueMapper.ofAtualizarCommand(command);
            SaidaEstoque saidaEstoqueAntigo = gateway.findById(idSaida).get();
            Double qtdAntiga = saidaEstoqueAntigo.getQtdSaida();
            Double qtdNova = saidaParaAtualizar.getQtdSaida();
            Double qtdAtualizado = qtdNova - qtdAntiga;
            ItemEstoque itemEstoque = atualizarQuantidadeUseCase.execute(saidaParaAtualizar, qtdAtualizado);
            notificarObservers(itemEstoque);
            return gateway.save(saidaParaAtualizar);
        }
        throw new SaidaEstoqueNaoEncontradoException("A saída não existe");
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
