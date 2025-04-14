package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Item de Estoque", description = "Operações CRUD relacionadas aos itens de estoque (tecido ou roupa).")
@RestController
@RequestMapping("/itens-estoque")
@RequiredArgsConstructor
public class ItemEstoqueController {
    private final ItemEstoqueService itemEstoqueService;

    @Operation(
            summary = "Cadastramento de um novo Item.",
            description = "Retorna um objeto do tipo ItemEstoqueResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item de Estoque cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "A descrição de Item informada já existe no sistema.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ItemEstoqueRequestDto para cadastro.",
            required = true
    )
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

    @Operation(summary = "Listagem de todos os Itens no Estoque.", description = "Retorna uma lista de ItemEstoqueResponseDto com todos os Itens no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui itens de estoque."),
            @ApiResponse(responseCode = "204", description = "Lista de itens está vazia"),
    })
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

    @Operation(summary = "Exibição de um item por ID", description = "Retorna um objeto ItemEstoqueResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemEstoqueResponseDto> buscarPorId(@PathVariable Integer id) {
        ItemEstoqueResponseDto itemEstoque = ItemEstoqueMapper.toResponseDto(
                itemEstoqueService.buscarItemEstoquePorId(id)
        );
        return ResponseEntity.status(200).body(itemEstoque);
    }

    @Operation(summary = "Atualização de Item.", description = "Retorna um objeto ItemEstoqueResponseDto atualizado com os valores de um ItemEstoqueRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ItemEstoqueRequestDto com valores de atualização.",
            required = true
    )
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

    @Operation(summary = "Deleção de um registro de Item.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(
            @PathVariable Integer id
    ) {
        itemEstoqueService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }
}
