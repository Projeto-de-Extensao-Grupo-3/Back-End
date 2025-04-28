package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.Prateleira;
import school.sptech.CRUDBackend.exception.Prateleira.PrateleiraConflitoException;
import school.sptech.CRUDBackend.exception.Prateleira.PrateleiraNaoEncontradaException;
import school.sptech.CRUDBackend.repository.PrateleiraRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrateleiraService {

    private final PrateleiraRepository repository;

    public Prateleira cadastrarPrateleira(Prateleira prateleiraParaCadastrar) {

        if (repository.existsByCodigo(prateleiraParaCadastrar.getCodigo())) {
            throw new PrateleiraConflitoException("Essa estante já existe");
        }
        return repository.save(prateleiraParaCadastrar);
    }

    public List<Prateleira> listarTodos() {
        return repository.findAll();
    }

    public Prateleira buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new PrateleiraNaoEncontradaException("Prateleira com ID " + id + " não encontrada"));
    }

    public Prateleira buscarPorCodigo(String codigo) {
        return repository.findByCodigo(codigo)
                .orElseThrow(() -> new PrateleiraNaoEncontradaException("Estante com código '" + codigo + "' não encontrada"));
    }

    public Prateleira atualizarPorId(Integer id, Prateleira prateleiraParaAtualizar) {

        if (repository.existsById(id)) {
            prateleiraParaAtualizar.setIdPrateleira(id);
            return repository.save(prateleiraParaAtualizar);
        }
        throw new PrateleiraNaoEncontradaException("A prateleira para atualizar não existe");
    }

    public void removerPorId(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PrateleiraNaoEncontradaException("Prateleira com ID " + id + " não encontrada");
        }
    }
}
