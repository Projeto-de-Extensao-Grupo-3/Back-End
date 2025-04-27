package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.exception.Lote.LoteNaoEncontradoException;
import school.sptech.CRUDBackend.exception.LoteItemEstoque.LoteItemEstoqueNaoEncontradoException;
import school.sptech.CRUDBackend.repository.LoteItemEstoqueRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoteItemEstoqueService {
    private final LoteItemEstoqueRepository repository;
    private final ItemEstoqueService itemEstoqueService;

    public LoteItemEstoque cadastrarLoteItemEstoque(LoteItemEstoque loteParaCadastrar) {
        atualizarQtdItemEstoque(loteParaCadastrar, 0.0);
        return repository.save(loteParaCadastrar);
    }

    public List<LoteItemEstoque> listarTodos(){
        return repository.findAll();
    }

    public LoteItemEstoque buscarLoteItemEstoquePorId(Integer id){

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
            atualizarQtdItemEstoque(loteItemEstoqueParaAtualizar, qtdAtualizado);
            return repository.save(loteItemEstoqueParaAtualizar);
        }
        throw new LoteNaoEncontradoException("O lote do estoque não foi encontrado");
    }

    public void removerPorId(Integer id){

        if (repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new LoteItemEstoqueNaoEncontradoException("O lote do item não existe");
        }
    }

    private void atualizarQtdItemEstoque(LoteItemEstoque loteItemEstoque, Double qtdAtualizar) {
        Integer idItemEstoque = loteItemEstoque.getItemEstoque().getIdItemEstoque();
        ItemEstoque itemEstoque = itemEstoqueService.buscarItemEstoquePorId(idItemEstoque);
        Double qtdEntradaNova = qtdAtualizar == 0
                ? loteItemEstoque.getQtdItem()
                : qtdAtualizar;
        itemEstoque.setQtdArmazenado(itemEstoque.getQtdArmazenado() + qtdEntradaNova);
        itemEstoqueService.atualizarItemEstoquePorId(idItemEstoque, itemEstoque);
    }
}
