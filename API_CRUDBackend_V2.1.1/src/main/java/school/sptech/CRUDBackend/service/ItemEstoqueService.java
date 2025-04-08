package school.sptech.CRUDBackend.service;

import lombok.Setter;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.exception.itensEstoque.ItemEstoqueConflitoException;
import school.sptech.CRUDBackend.exception.itensEstoque.ItemEstoqueNaoEncontradoException;
import school.sptech.CRUDBackend.repository.ItemEstoqueRepository;

import java.util.List;
import java.util.Optional;

@Service
@Setter
public class ItemEstoqueService {
    private ItemEstoqueRepository repository;

    public ItemEstoque cadastrarItemEstoque(ItemEstoque itemCadastrar) {
        if(repository.existsByDescricao(itemCadastrar)) {
            return repository.save(itemCadastrar);
        }
        throw new ItemEstoqueConflitoException("Este produto já existe no sistema.");
    }

    public List<ItemEstoque> verificarTodosItemEstoque() {
        return repository.findAll();
    }

    public ItemEstoque buscarItemEstoquePorId(Integer id) {
        Optional<ItemEstoque> itemExiste = repository.findById(id);

        return itemExiste.map(item ->
                itemExiste.get())
                .orElseThrow(() -> new ItemEstoqueNaoEncontradoException("O item procurado não existe"));
    }

    public ItemEstoque atualizarItemEstoquePorId(Integer id, ItemEstoque itemAtualizar) {
        if(repository.existsById(id)) {
            itemAtualizar.setId(id);
            return repository.save(itemAtualizar);
        }
        throw new ItemEstoqueNaoEncontradoException("O item para atualizar não existe");
    }

    public void removerPorId(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new ItemEstoqueNaoEncontradoException("O item para atualizar não existe");
    }
}
