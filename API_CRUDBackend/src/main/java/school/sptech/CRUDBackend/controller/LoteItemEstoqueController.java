package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.LoteItemEstoque.LoteItemEstoqueMapper;
import school.sptech.CRUDBackend.dto.LoteItemEstoque.LoteItemEstoqueRequestDto;
import school.sptech.CRUDBackend.dto.LoteItemEstoque.LoteItemEstoqueResponseDto;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.service.LoteItemEstoqueService;

import java.util.List;

@Tag(name = "Lote de Quantidade e Preço do Item", description = "Operações CRUD relacionadas aos lotes de quantidade e preço dos Itens.")
@RestController
@RequestMapping("/lotes-item-estoque")
@RequiredArgsConstructor
public class LoteItemEstoqueController {
    private final LoteItemEstoqueService service;

    @Operation(
            summary = "Cadastro de um novo Lote de Item.",
            description = "Retorna um objeto do tipo LoteItemEstoqueResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lote de Item cadastrado com sucesso.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo LoteItemEstoqueRequestDto para cadastro.",
            required = true
    )
    @PostMapping
    public ResponseEntity<LoteItemEstoqueResponseDto> cadastrar(
            @RequestBody @Valid LoteItemEstoqueRequestDto loteItemEstoqueCadastrar
    ) {
      LoteItemEstoque loteItemEstoqueParaCadastrar = LoteItemEstoqueMapper.toEntity(loteItemEstoqueCadastrar);
      LoteItemEstoqueResponseDto loteItemEstoqueCadastrado = LoteItemEstoqueMapper.toResponseDto(
              service.cadastrarLoteItemEstoque(loteItemEstoqueParaCadastrar)
      );
      return ResponseEntity.status(201).body(loteItemEstoqueCadastrado);
    }

    @Operation(summary = "Listagem de todos lotes de itens no sistema.", description = "Retorna uma lista de LoteItemEstoqueResponseDto com todos os lotes de itens no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "lista possui Lotes de Itens"),
            @ApiResponse(responseCode = "204", description = "lista de Lotes está vazia"),
    })
    @GetMapping
    public ResponseEntity<List<LoteItemEstoqueResponseDto>> listarTodos(){

        List<LoteItemEstoqueResponseDto> todosOsLoteItemEstoque = LoteItemEstoqueMapper.toResponseDtos(
                service.listarTodos()
        );
        if (todosOsLoteItemEstoque.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(todosOsLoteItemEstoque);
    }

    @Operation(summary = "Exibição de um Lote de Item por ID", description = "Retorna um objeto LoteItemEstoqueResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID informado no PathVariable foi encontrado."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<LoteItemEstoqueResponseDto> buscarPorId(@PathVariable Integer id){
        LoteItemEstoqueResponseDto loteItemEstoqueEcontrado = LoteItemEstoqueMapper.toResponseDto(
                service.buscarLoteItemEstoquePorId(id)
        );
        return ResponseEntity.status(200).body(loteItemEstoqueEcontrado);
    }

    @Operation(summary = "Atualização de Lote de Item.", description = "Retorna um objeto LoteItemEstoqueResponseDto atualizado com os valores de um LoteItemEstoqueRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID informado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo CorteTecidoRequestDto com valores de atualização.",
            required = true
    )
    @PutMapping("/{id}")
    public ResponseEntity<LoteItemEstoqueResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid LoteItemEstoqueRequestDto loteItemEstoqueCadastrar
    ) {
        LoteItemEstoque loteItemEstoqueParaAtualizar = LoteItemEstoqueMapper.toEntity(loteItemEstoqueCadastrar);
        LoteItemEstoqueResponseDto loteItemEstoqueAtualizado = LoteItemEstoqueMapper.toResponseDto(
                service.atualizarLoteItemEstoquePorId(id, loteItemEstoqueParaAtualizar)
        );
        return ResponseEntity.status(200).body(loteItemEstoqueAtualizado);
    }

    @Operation(summary = "Deleção de um registro de lote de item.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote de Item deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID informado no PathVariable foi encontrado."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(
            @PathVariable Integer id)
    {
       service.removerPorId(id);
       return ResponseEntity.status(200).build();
    }
}
