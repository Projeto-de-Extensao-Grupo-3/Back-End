package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.Model.itemEstoque.Observer;
import school.sptech.CRUDBackend.Model.itemEstoque.Subject;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.entity.SaidaEstoque;
import school.sptech.CRUDBackend.exception.SaidaEstoque.SaidaEstoqueNaoEncontradoException;
import school.sptech.CRUDBackend.repository.SaidaEstoqueRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaidaEstoqueService implements Subject {
    private final SaidaEstoqueRepository repository;
    private final LoteItemEstoqueService loteItemEstoqueService;

    private final List<Observer> observadores = new ArrayList<>();

    public SaidaEstoque cadastrar(SaidaEstoque saidaParaCadastrar){
        ItemEstoque itemEstoque = atualizarQtdItemEstoque(saidaParaCadastrar, 0.0);
        notificarObservers(itemEstoque);
        return repository.save(saidaParaCadastrar);
    }

    public List<SaidaEstoque> listar(){
        return repository.findAll();
    }

    public SaidaEstoque buscarPorId(Integer id){
        Optional<SaidaEstoque> saidaPossivel = repository.findById(id);

        return saidaPossivel.orElseThrow(()
                -> new SaidaEstoqueNaoEncontradoException("A saída procurada não existe"));
    }

    public List<SaidaEstoque> buscarPorMotivo(String motivo){
        List<SaidaEstoque> saidaPorMotivo = repository.findByMotivoSaida(motivo);

        if (saidaPorMotivo.isEmpty()){
            throw new SaidaEstoqueNaoEncontradoException("Nenhuma saída com este motivo foi encontrada");
        }
        return saidaPorMotivo;
    }

    public List<SaidaEstoque> buscarPorData(LocalDate data){
        List<SaidaEstoque> saidaPorData = repository.findByData(data);

        if (saidaPorData.isEmpty()){
            throw new SaidaEstoqueNaoEncontradoException("Nenhuma saída para esta data foi encontrada");
        }
        return saidaPorData;
    }

    public SaidaEstoque atualizarPorId(Integer id, SaidaEstoque saidaParaAtualizar){
        if (repository.existsById(id)){
            saidaParaAtualizar.setIdSaidaEstoque(id);
            SaidaEstoque saidaEstoqueAntigo = buscarPorId(id);
            Double qtdAntiga = saidaEstoqueAntigo.getQtdSaida();
            Double qtdNova = saidaParaAtualizar.getQtdSaida();
            Double qtdAtualizado = qtdNova - qtdAntiga;
            ItemEstoque itemEstoque = atualizarQtdItemEstoque(saidaParaAtualizar, qtdAtualizado);
            notificarObservers(itemEstoque);
            return repository.save(saidaParaAtualizar);
        }
        throw new SaidaEstoqueNaoEncontradoException("A saída não existe");
    }

    public void removerPorId(Integer id){

        if (repository.existsById(id)){
            repository.deleteById(id);
        }
        else {
            throw new SaidaEstoqueNaoEncontradoException("A saída não existe para remover");
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

    private ItemEstoque atualizarQtdItemEstoque(SaidaEstoque saidaEstoque, Double qtdAtualizar) {
        Integer idLoteItemEstoque = saidaEstoque.getLoteItemEstoque().getIdLoteItemEstoque();
        LoteItemEstoque loteItemEstoque = loteItemEstoqueService.buscarLoteItemEstoquePorId(idLoteItemEstoque);
        ItemEstoque itemEstoque = loteItemEstoque.getItemEstoque();
        Double qtdEntradaNova = qtdAtualizar == 0.0
                ? saidaEstoque.getQtdSaida()
                : qtdAtualizar;
        itemEstoque.setQtdArmazenado(itemEstoque.getQtdArmazenado() - qtdEntradaNova);
        return itemEstoque;
    }
}
