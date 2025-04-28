package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.Categoria;
import school.sptech.CRUDBackend.exception.Categoria.CategoriaConflitoException;
import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CRUDBackend.repository.CategoriaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria cadastrarCategoria(Categoria categoriaParaCadastrar){

        if (repository.existsByNome(categoriaParaCadastrar.getNome())){
            throw new CategoriaConflitoException("Essa categoria já existe");
        }
        return repository.save(categoriaParaCadastrar);
    }

    public List<Categoria> listarTodos() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria com ID " + id + " não encontrada"));
    }

    public Categoria buscarPorNome(String nome) {
        return repository.findByNome(nome)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria com nome '" + nome + "' não encontrada"));
    }

    public Categoria atualizarPorId(Integer id, Categoria categoriaParaAtualizar) {

        if (repository.existsById(id)) {
            categoriaParaAtualizar.setIdCategoria(id);
            return repository.save(categoriaParaAtualizar);
        }
        throw new CategoriaNaoEncontradaException("A categoria para atualizar não existe");
    }

    public void removerPorId(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new CategoriaNaoEncontradaException("Categoria com ID " + id + " não encontrada");
        }
    }
}
