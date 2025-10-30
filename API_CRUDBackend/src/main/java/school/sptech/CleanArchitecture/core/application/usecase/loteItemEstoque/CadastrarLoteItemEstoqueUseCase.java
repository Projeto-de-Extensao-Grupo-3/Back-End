package school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque;

import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.CriarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.BuscarFuncionarioPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueAtualizarDadosUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueAtualizarQuantidadeUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueBuscarPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.parceiro.BuscarParceiroPorIdUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;
import school.sptech.CleanArchitecture.core.domain.observer.ObserverLoteItem;
import school.sptech.CleanArchitecture.core.domain.observer.Subject;
import school.sptech.CleanArchitecture.core.domain.observer.SubjectLoteItem;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.LoteItemEstoqueMapper;

import java.util.List;
import java.util.ArrayList;

public class CadastrarLoteItemEstoqueUseCase implements SubjectLoteItem, Subject {

    private final LoteItemEstoqueGateway gateway;

    private final ItemEstoqueAtualizarQuantidadeUseCase itemEstoqueAtualizarUseCase;

    private final ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarUseCase;

    private final BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase;

    //private final ItemEstoqueAtualizarDadosUseCase atualizarDadosUseCase;

    private final List<Observer> observadores = new ArrayList<>();
    private final List<ObserverLoteItem> observadoresLoteItem = new ArrayList<>();

    public CadastrarLoteItemEstoqueUseCase(LoteItemEstoqueGateway gateway, ItemEstoqueAtualizarQuantidadeUseCase itemEstoqueAtualizarUseCase, ItemEstoqueBuscarPorIdUseCase itemEstoqueBuscarUseCase, BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase) {
        this.gateway = gateway;
        this.itemEstoqueAtualizarUseCase = itemEstoqueAtualizarUseCase;
        this.itemEstoqueBuscarUseCase = itemEstoqueBuscarUseCase;
        this.loteItemEstoqueUseCase = loteItemEstoqueUseCase;
    }

    public LoteItemEstoque executar(CriarLoteItemEstoqueCommand command){

        // um loteItemEstoque deve retornar dentro dele um itemEstoque e um lote
        // LoteItemEstoque loteItemEstoque = LoteItemEstoqueMapper.ofCadastrarCommand(command);

        //  LoteItemEstoque com os id's de itemEstoque e lote
        // ItemEstoque itemEstoque = atualizarDadosUseCase.execute(loteItemEstoque, 0.0);

        // Atualização da quantidade de ItemEstoque
        LoteItemEstoque loteItemEstoque = loteItemEstoqueUseCase.executar(command.itemEstoque());
        ItemEstoque itemEstoque = itemEstoqueBuscarUseCase.execute(loteItemEstoque.getItemEstoque().getIdItemEstoque());
        Double qtdEntradaNova = command.qtdItem();
        itemEstoque.setQtdArmazenado(itemEstoque.getQtdArmazenado() + qtdEntradaNova);
        itemEstoqueAtualizarUseCase.atualizarQuantidade(itemEstoque);
        /*--------------------------------------------------------------------------------------*/

        notificarObservers(itemEstoque);
        notificarObserversLoteItem(itemEstoque);
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
