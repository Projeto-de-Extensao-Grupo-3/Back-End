package school.sptech.CRUDBackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.itemEstoque.ItemEstoqueMapper;
import school.sptech.CRUDBackend.dto.itemEstoque.ItemEstoqueRequestDto;
import school.sptech.CRUDBackend.dto.itemEstoque.ItemEstoqueResponseDto;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.service.ItemEstoqueService;

import java.util.List;

@RestController
@RequestMapping("/itens-estoque")
@RequiredArgsConstructor
public class ItemEstoqueController {
    private final ItemEstoqueService itemEstoqueService;

    @PostMapping
    public ResponseEntity<ItemEstoqueResponseDto> cadastrar(
            @RequestBody @Valid ItemEstoqueRequestDto itemEstoqueCadastrar
    ) {
        ItemEstoque itemParaCadastrar = ItemEstoqueMapper.toEntity(itemEstoqueCadastrar);
        ItemEstoqueResponseDto itemCadastrado = ItemEstoqueMapper.toResponseDto(
                itemEstoqueService.cadastrarItemEstoque(itemParaCadastrar)
        );
        return ResponseEntity.status(201).body(itemCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<ItemEstoqueResponseDto>> verificarTodos() {
        List<ItemEstoqueResponseDto> todosItens = ItemEstoqueMapper.toResponseDtos(
                itemEstoqueService.verificarTodosItemEstoque()
        );
        if(todosItens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(todosItens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEstoqueResponseDto> buscarPorId(@PathVariable Integer id) {
        ItemEstoqueResponseDto itemEstoque = ItemEstoqueMapper.toResponseDto(
                itemEstoqueService.buscarItemEstoquePorId(id)
        );
        return ResponseEntity.status(200).body(itemEstoque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemEstoqueResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid ItemEstoqueRequestDto itemEstoqueAtualizar
    ) {
        ItemEstoque itemParaAtualizar = ItemEstoqueMapper.toEntity(itemEstoqueAtualizar);
        ItemEstoqueResponseDto itemAtualizado = ItemEstoqueMapper.toResponseDto(
                itemEstoqueService.atualizarItemEstoquePorId(id, itemParaAtualizar)
        );
        return ResponseEntity.status(200).body(itemAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(
            @PathVariable Integer id
    ) {
        itemEstoqueService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }
}
