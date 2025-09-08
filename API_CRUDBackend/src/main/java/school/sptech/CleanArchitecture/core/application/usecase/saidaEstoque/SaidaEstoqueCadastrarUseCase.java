package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.SaidaEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.mapper.SaidaItemEstoqueMapper;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;
import school.sptech.CleanArchitecture.core.domain.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class SaidaEstoqueCadastrarUseCase implements Subject {

    private final SaidaEstoqueGateway saidaGateway;

    private final LoteItemEstoqueGateway loteGateway;

    private final List<Observer> observadores = new ArrayList<>();

    public SaidaEstoqueCadastrarUseCase(SaidaEstoqueGateway saidaGateway, LoteItemEstoqueGateway loteGateway) {
        this.saidaGateway = saidaGateway;
        this.loteGateway = loteGateway;
    }

    public SaidaEstoque execute(SaidaEstoqueCadastrarCommand command){
        SaidaEstoque saidaEstoque = SaidaItemEstoqueMapper.ofCadastrarCommand(command);

        Integer idLoteItemEstoque = saidaEstoque.getLoteItemEstoque().getIdLoteItemEstoque();
        LoteItemEstoque loteItemEstoque = loteGateway.buscarPorId(idLoteItemEstoque);

        ItemEstoque itemEstoque = loteItemEstoque.getItemEstoque();

        Double qtdEntradaNova = command.qtdAtualizar() == 0.0
                ? saidaEstoque.getQtdSaida()
                : command.qtdAtualizar();

        itemEstoque.atualizarQuantidade(qtdEntradaNova);

        notificarObservers(itemEstoque);
        return saidaGateway.save(saidaEstoque);
    }

    @Override
    public void adicionarObservador(Observer observador) {
            observadores.add(observador);
    }

    @Override
    public void removerObservador(Observer observador) {

    }

    @Override
    public void notificarObservers(school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque itemEstoque) {
        for (Observer observador : observadores) {
            observador.atualizarQuantidade(itemEstoque);
        }
    }
}
