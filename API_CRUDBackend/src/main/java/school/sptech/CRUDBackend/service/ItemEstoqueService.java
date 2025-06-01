package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.Model.itemEstoque.Observer;
import school.sptech.CRUDBackend.entity.ConfeccaoRoupa;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.exception.itensEstoque.ItemEstoqueConflitoException;
import school.sptech.CRUDBackend.exception.itensEstoque.ItemEstoqueNaoEncontradoException;
import school.sptech.CRUDBackend.repository.ItemEstoqueRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ItemEstoqueService implements Observer {
    private final ItemEstoqueRepository itemEstoqueRepository;

    public ItemEstoque cadastrarItemEstoque(ItemEstoque itemCadastrar) {
        if(itemEstoqueRepository.existsByDescricao(itemCadastrar.getDescricao())) {
            throw new ItemEstoqueConflitoException("Este item já existe no sistema.");
        }
        return itemEstoqueRepository.save(itemCadastrar);
    }

    public ItemEstoque cadastrarTecidosDaRoupa(Integer idRoupa, Set<ConfeccaoRoupa> tecidos) {
        if (itemEstoqueRepository.existsById(idRoupa)) {
            ItemEstoque roupa = itemEstoqueRepository.findById(idRoupa).get();
            roupa.setConfeccaoRoupa(tecidos);
            return itemEstoqueRepository.save(roupa);
        }
        throw new ItemEstoqueNaoEncontradoException("A roupa não existe no estoque");
    }

    public List<ItemEstoque> verificarTodosItemEstoque() {
        return itemEstoqueRepository.findAll();
    }

    public ItemEstoque buscarItemEstoquePorId(Integer id) {
        return itemEstoqueRepository.findById(id)
                .orElseThrow(() -> new ItemEstoqueNaoEncontradoException("O item procurado não existe"));
    }

    public ItemEstoque atualizarItemEstoquePorId(Integer id, ItemEstoque itemAtualizar) {
        if (itemEstoqueRepository.existsById(id)) {
            itemAtualizar.setIdItemEstoque(id);
            return itemEstoqueRepository.save(itemAtualizar);
        }
        throw new ItemEstoqueNaoEncontradoException("O item para atualizar não existe");
    }

    public void removerPorId(Integer id) {
        if (itemEstoqueRepository.existsById(id)) {
            itemEstoqueRepository.deleteById(id);
        } else {
            throw new ItemEstoqueNaoEncontradoException("O item para atualizar não existe");
        }
    }

    public void atualizarQuantidade(ItemEstoque itemEstoque) {
        atualizarItemEstoquePorId(itemEstoque.getIdItemEstoque(), itemEstoque);
    }
}
