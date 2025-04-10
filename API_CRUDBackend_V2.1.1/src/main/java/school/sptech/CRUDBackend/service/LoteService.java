package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.Lote;
import school.sptech.CRUDBackend.exception.Lote.LoteConflitoException;
import school.sptech.CRUDBackend.exception.Lote.LoteNaoEncontradoException;
import school.sptech.CRUDBackend.repository.LoteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoteService {

    private final LoteRepository repository;

    public Lote cadastrarLote(Lote loteParaCadastrar){

        if (repository.existsByDescricao(loteParaCadastrar.getDescricao())){
            throw new LoteConflitoException("Este lote já existe no sistema");
        }
        return repository.save(loteParaCadastrar);
    }

    public List<Lote> listarTodosOsLotes(){
        return repository.findAll();
    }

    public Lote buscarLotePorId(Integer id){

        Optional<Lote> existeLote = repository.findById(id);

        return existeLote.map(lote ->
                existeLote.get()).orElseThrow(() -> new LoteNaoEncontradoException("O lote procurado não existe"));
    }

    public Lote atualizarLotePorId(Integer id, Lote loteParaAtualizar){

        if (repository.existsById(id)){
            loteParaAtualizar.setIdLote(id);
            return repository.save(loteParaAtualizar);
        }
        throw new LoteNaoEncontradoException("O lote para atualizar não existe");
    }

    public void removerPorId(Integer id){

        if (repository.existsById(id)){
            repository.deleteById(id);
        }
        throw new LoteNaoEncontradoException("O lote para remover não existe");
    }
}
