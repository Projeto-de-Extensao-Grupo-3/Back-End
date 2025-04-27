package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.entity.SaidaEstoque;
import school.sptech.CRUDBackend.exception.SaidaEstoque.SaidaEstoqueNaoEncontradoException;
import school.sptech.CRUDBackend.repository.SaidaEstoqueRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaidaEstoqueService {
    private final SaidaEstoqueRepository repository;
    private final LoteItemEstoqueService loteItemEstoqueService;
    private final ItemEstoqueService itemEstoqueService;

    public SaidaEstoque cadastrar(SaidaEstoque saidaParaCadastrar){
        atualizarQtdItemEstoque(saidaParaCadastrar, 0.0);
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

    public SaidaEstoque atualizarPorId(Integer id, SaidaEstoque saidaParaAtualizar){
        if (repository.existsById(id)){
            saidaParaAtualizar.setIdSaida(id);
            SaidaEstoque saidaEstoqueAntigo = buscarPorId(id);
            Double qtdAntiga = saidaEstoqueAntigo.getQtdSaida();
            Double qtdNova = saidaParaAtualizar.getQtdSaida();
            Double qtdAtualizado = qtdNova - qtdAntiga;
            atualizarQtdItemEstoque(saidaParaAtualizar, qtdAtualizado);
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

    private void atualizarQtdItemEstoque(SaidaEstoque saidaEstoque, Double qtdAtualizar) {
        Integer idLoteItemEstoque = saidaEstoque.getLoteItemEstoque().getIdLoteItemEstoque();
        LoteItemEstoque loteItemEstoque = loteItemEstoqueService.buscarLoteItemEstoquePorId(idLoteItemEstoque);
        ItemEstoque itemEstoque = loteItemEstoque.getItemEstoque();
        Double qtdEntradaNova = qtdAtualizar == 0.0
                ? saidaEstoque.getQtdSaida()
                : qtdAtualizar;
        itemEstoque.setQtdArmazenado(itemEstoque.getQtdArmazenado() - qtdEntradaNova);
        itemEstoqueService.atualizarItemEstoquePorId(itemEstoque.getIdItemEstoque(), itemEstoque);
    }
}
