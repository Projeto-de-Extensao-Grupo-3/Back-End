package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.Model.itemEstoque.Observer;
import school.sptech.CRUDBackend.Model.itemEstoque.Subject;
import school.sptech.CRUDBackend.Model.loteItemEstoque.ObserverLoteItem;
import school.sptech.CRUDBackend.Model.loteItemEstoque.SubjectLoteItem;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.exception.LoteItemEstoque.LoteItemEstoqueNaoEncontradoException;
import school.sptech.CRUDBackend.repository.LoteItemEstoqueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoteItemEstoqueService implements SubjectLoteItem, Subject {
    private final LoteItemEstoqueRepository repository;
    private final ItemEstoqueService itemEstoqueService;

    private final List<Observer> observadores = new ArrayList<>();
    private final List<ObserverLoteItem> observadoresLoteItem = new ArrayList<>();

    public LoteItemEstoque cadastrarLoteItemEstoque(LoteItemEstoque loteParaCadastrar) {
        ItemEstoque itemEstoque = atualizarDadosItemEstoque(loteParaCadastrar, 0.0, 0.0);
        notificarObservers(itemEstoque);
        notificarObserversLoteItem(itemEstoque);
        return repository.save(loteParaCadastrar);
    }

    public List<LoteItemEstoque> listarTodos(){
        return repository.findAll();
    }

    public LoteItemEstoque buscarLoteItemEstoquePorId(Integer id) {

        Optional<LoteItemEstoque> loteItemEstoqueExistente = repository.findById(id);

        return loteItemEstoqueExistente.orElseThrow(()
                -> new LoteItemEstoqueNaoEncontradoException("O este lote do item não foi encontrado"));
    }

    public LoteItemEstoque atualizarLoteItemEstoquePorId(Integer id, LoteItemEstoque loteItemEstoqueParaAtualizar){
        if (repository.existsById(id)) {
            loteItemEstoqueParaAtualizar.setIdLoteItemEstoque(id);
            LoteItemEstoque loteItemEstoqueAntigo = buscarLoteItemEstoquePorId(id);
            Double qtdAntiga = loteItemEstoqueAntigo.getQtdItem();
            Double qtdNova = loteItemEstoqueParaAtualizar.getQtdItem();
            Double qtdAtualizado = qtdNova - qtdAntiga;
            Double precoAtualizado = loteItemEstoqueParaAtualizar.getPreco();
            atualizarDadosItemEstoque(loteItemEstoqueParaAtualizar, qtdAtualizado, precoAtualizado);
            return repository.save(loteItemEstoqueParaAtualizar);
        }
        throw new LoteItemEstoqueNaoEncontradoException("O lote do estoque não foi encontrado");
    }

    public void removerPorId(Integer id){

        if (repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new LoteItemEstoqueNaoEncontradoException("O lote do item não existe");
        }
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
    public void notificarObservers(ItemEstoque itemEstoque){
        for (Observer observador : observadores) {
            observador.atualizarQuantidade(itemEstoque);
        }
    }

    @Override
    public void adicionarObservadorLoteItem(ObserverLoteItem observerLoteItem){ observadoresLoteItem.add(observerLoteItem);}

    @Override
    public void removerObservadorLoteItem(ObserverLoteItem observerLoteItem){
        observadoresLoteItem.remove(observerLoteItem);
    }

    @Override
    public void notificarObserversLoteItem(ItemEstoque itemEstoque){
        for (ObserverLoteItem observador : observadoresLoteItem) {
            observador.atualizarPreco(itemEstoque);
        }
    }

    private ItemEstoque atualizarDadosItemEstoque(LoteItemEstoque loteItemEstoque, Double qtdAtualizar, Double precoAtualizar) {
        Integer idItemEstoque = loteItemEstoque.getItemEstoque().getIdItemEstoque();
        ItemEstoque itemEstoque = itemEstoqueService.buscarItemEstoquePorId(idItemEstoque);
        Double qtdEntradaNova = qtdAtualizar == 0
                ? loteItemEstoque.getQtdItem()
                : qtdAtualizar;
        itemEstoque.setQtdArmazenado(itemEstoque.getQtdArmazenado() + qtdEntradaNova);

        String descricaoItem = itemEstoque.getDescricao();
        if (descricaoItem != null && descricaoItem.toLowerCase().contains("tecido")){
            itemEstoque.setPreco(precoAtualizar == 0 ? loteItemEstoque.getPreco() : precoAtualizar);
        }

        return itemEstoque;
    }
}
