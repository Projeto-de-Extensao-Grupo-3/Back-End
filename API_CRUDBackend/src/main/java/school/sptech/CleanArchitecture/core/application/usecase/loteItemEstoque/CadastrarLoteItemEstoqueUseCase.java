package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.CriarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;
import school.sptech.CleanArchitecture.core.domain.observer.ObserverLoteItem;
import school.sptech.CleanArchitecture.core.domain.observer.Subject;
import school.sptech.CleanArchitecture.core.domain.observer.SubjectLoteItem;

import java.util.List;
import java.util.ArrayList;

public class CadastrarLoteItemEstoqueUseCase implements SubjectLoteItem, Subject {

    private final LoteItemEstoqueGateway gateway;

    private final List<Observer> observadores = new ArrayList<>();
    private final List<ObserverLoteItem> observadoresLoteItem = new ArrayList<>();

    public CadastrarLoteItemEstoqueUseCase(LoteItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public LoteItemEstoque executar(CriarLoteItemEstoqueCommand command){
        LoteItemEstoque loteItemEstoque = new LoteItemEstoque(
                command.qtdItem(),
                command.preco(),
                command.itemEstoque().getIdItemEstoque(),
                command.lote().getIdLote()
        );
        return gateway.save(loteItemEstoque);
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

    @Override
    public void adicionarObservadorLoteItem(ObserverLoteItem observadorLoteItem) {
        observadoresLoteItem.add(observadorLoteItem);
    }

    @Override
    public void removerObservadorLoteItem(ObserverLoteItem observadorLoteItem) {
        observadoresLoteItem.remove(observadorLoteItem);
    }

    @Override
    public void notificarObserversLoteItem(ItemEstoque itemEstoque) {
        for (ObserverLoteItem observador : observadoresLoteItem) {
            observador.atualizarPreco(itemEstoque);
        }
    }
}
