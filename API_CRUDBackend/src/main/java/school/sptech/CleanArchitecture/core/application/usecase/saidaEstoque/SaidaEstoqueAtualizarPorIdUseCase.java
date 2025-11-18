package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.itemEstoque.ItemEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.SaidaEstoqueAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.exceptions.SaidaEstoque.SaidaEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.application.mapper.ItemEstoqueMapper;
import school.sptech.CleanArchitecture.core.application.mapper.SaidaEstoqueMapper;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.BuscarFuncionarioPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueAtualizarPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque.BuscarPorIdLoteItemEstoqueUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.parceiro.BuscarParceiroPorIdUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;
import school.sptech.CleanArchitecture.core.domain.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class SaidaEstoqueAtualizarPorIdUseCase implements Subject {

    private final SaidaEstoqueGateway gateway;

    private final SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarQuantidadeUseCase;

    private final BuscarParceiroPorIdUseCase parceiroPorIdUseCase;

    private final BuscarFuncionarioPorIdUseCase funcionarioPorIdUseCase;

    private final BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase;

    private final ItemEstoqueAtualizarPorIdUseCase atualizarPorIdUseCase;

    private final List<Observer> observadores = new ArrayList<>();

    public SaidaEstoqueAtualizarPorIdUseCase(SaidaEstoqueGateway gateway, SaidaEstoqueAtualizarQuantidadeLoteDeItemUseCase atualizarQuantidadeUseCase, BuscarParceiroPorIdUseCase parceiroPorIdUseCase, BuscarFuncionarioPorIdUseCase funcionarioPorIdUseCase, BuscarPorIdLoteItemEstoqueUseCase loteItemEstoqueUseCase, ItemEstoqueAtualizarPorIdUseCase atualizarPorIdUseCase) {
        this.gateway = gateway;
        this.atualizarQuantidadeUseCase = atualizarQuantidadeUseCase;
        this.parceiroPorIdUseCase = parceiroPorIdUseCase;
        this.funcionarioPorIdUseCase = funcionarioPorIdUseCase;
        this.loteItemEstoqueUseCase = loteItemEstoqueUseCase;
        this.atualizarPorIdUseCase = atualizarPorIdUseCase;
    }

    public SaidaEstoque execute(SaidaEstoqueAtualizarPorIdCommand command){
        Integer idSaida = command.idSaidaEstoque();
        if (gateway.existsById(idSaida)){
            SaidaEstoque saidaParaAtualizar = SaidaEstoqueMapper.ofAtualizarCommand(command);
            SaidaEstoque saidaEstoqueAntigo = gateway.findById(idSaida).get();

            saidaParaAtualizar.setResponsavel(
                    funcionarioPorIdUseCase.execute(saidaParaAtualizar.getResponsavel().getIdFuncionario()));
            saidaParaAtualizar.setCostureira(saidaParaAtualizar.getCostureira().getId()  == null ? null :
                    parceiroPorIdUseCase.execute(saidaParaAtualizar.getCostureira().getId()));
            saidaParaAtualizar.setLoteItemEstoque(
                    loteItemEstoqueUseCase.executar(saidaParaAtualizar.getLoteItemEstoque().getIdLoteItemEstoque()));

            Double qtdAntiga = saidaEstoqueAntigo.getQtdSaida();
            Double qtdNova = saidaParaAtualizar.getQtdSaida();
            Double qtdAtualizado = qtdNova - qtdAntiga;
            ItemEstoque itemEstoque = atualizarQuantidadeUseCase.execute(saidaParaAtualizar, qtdAtualizado);
            ItemEstoqueAtualizarPorIdCommand atualizarCommand = ItemEstoqueMapper.toAtualizarCommand(itemEstoque);
            atualizarPorIdUseCase.execute(atualizarCommand);

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
