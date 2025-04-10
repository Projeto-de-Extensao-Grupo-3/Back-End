package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public LoteItemEstoque cadastrarLoteItemEstoque(LoteItemEstoque loteParaCadastrar){

       return repository.save(loteParaCadastrar);
    }

    public List<LoteItemEstoque> listarTodos(){
        return repository.findAll();
    }

    public LoteItemEstoque buscarLoteItemEstoquePorId(Integer id){

        Optional<LoteItemEstoque> loteItemEstoqueExistente = repository.findById(id);

        return loteItemEstoqueExistente.map(intemEst ->
                loteItemEstoqueExistente.get()).orElseThrow(() -> new LoteItemEstoqueNaoEncontradoException("O este lote do item não foi encontrado"));
    }

    public LoteItemEstoque atualizarLoteItemEstoquePorId(Integer id, LoteItemEstoque loteItemEstoqueParaAtualizar){

        if (repository.existsById(id)){
            loteItemEstoqueParaAtualizar.setId(id);
            return repository.save(loteItemEstoqueParaAtualizar);
        }
        throw new LoteNaoEncontradoException("O lote do estoque não foi encontrado");
    }
}
