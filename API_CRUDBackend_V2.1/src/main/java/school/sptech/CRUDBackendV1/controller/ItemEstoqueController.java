package school.sptech.CRUDBackendV1.controller;

import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackendV1.entity.ItemEstoque;
import school.sptech.CRUDBackendV1.service.ItemEstoqueService;

import java.util.List;

@RestController
@RequestMapping("/itens-estoque")
@Setter
public class ItemEstoqueController {

    private ItemEstoqueService itemEstoqueService;

    @PostMapping
    public ResponseEntity<ItemEstoque> cadastrar(
            @RequestBody ItemEstoque itemEstoqueCadastrar
    ) {
        ItemEstoque itemEstoqueCadastrado = itemEstoqueService.cadastrarItemEstoque(itemEstoqueCadastrar);
        return ResponseEntity.status(201).body(itemEstoqueCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<ItemEstoque>> verificarTodos() {
        List<ItemEstoque> trouxeTodos = itemEstoqueService.verificarTodosItemEstoque();
        if(trouxeTodos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(trouxeTodos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEstoque> buscarPorId(@PathVariable Integer id) {
        ItemEstoque achouItemEstoque = itemEstoqueService.buscarItemEstoquePorId(id);
        return ResponseEntity.status(200).body(achouItemEstoque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemEstoque> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody ItemEstoque itemEstoqueAtualizar
    ) {
        ItemEstoque atualizouItemEstoque = itemEstoqueService.atualizarItemEstoquePorId(id, itemEstoqueAtualizar);
        return ResponseEntity.status(200).body(atualizouItemEstoque);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(
            @PathVariable Integer id
    ) {
        itemEstoqueService.removerPorId(id);
        return ResponseEntity.status(200).build();
    }
}
