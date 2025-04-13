package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.SaidaEstoque;
import school.sptech.CRUDBackend.exception.SaidaEstoque.SaidaEstoqueNaoEncontradoException;
import school.sptech.CRUDBackend.repository.SaidaEstoqueRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaidaEstoqueService {

    private final SaidaEstoqueRepository repository;

    public SaidaEstoque cadastrar(SaidaEstoque saidaParaCadastrar){

        return repository.save(saidaParaCadastrar);
    }

    public List<SaidaEstoque> listar(){
        return repository.findAll();
    }

    public SaidaEstoque buscarPorId(Integer id){
        Optional<SaidaEstoque> saidaPossivel = repository.findById(id);

        return saidaPossivel.map(saida ->
                saidaPossivel.get()).orElseThrow(()-> new SaidaEstoqueNaoEncontradoException("A saída procurada não existe"));
    }

    public List<SaidaEstoque> buscarPorMotivo(String motivo){
        List<SaidaEstoque> saidaPorMotivo = repository.findByMotivoSaida(motivo);

        if (saidaPorMotivo.isEmpty()){
            throw new SaidaEstoqueNaoEncontradoException("Nenhuma saída com este motivo foi encontrada");
        }
        return saidaPorMotivo;
    }

    public SaidaEstoque atualizarPorId(Integer id, SaidaEstoque saidaParaAtualizar){
        if (repository.existsById(id)){
            saidaParaAtualizar.setIdSaida(id);
            return repository.save(saidaParaAtualizar);
        }
        throw new SaidaEstoqueNaoEncontradoException("A saída não existe");
    }

    public void removerPorId(Integer id){

        if (repository.existsById(id)){
            repository.deleteById(id);
        }
        throw new SaidaEstoqueNaoEncontradoException("A saída não existe para remover");
    }
}
