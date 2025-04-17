package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.Lote.LoteMapper;
import school.sptech.CRUDBackend.dto.Lote.LoteRequestDto;
import school.sptech.CRUDBackend.dto.Lote.LoteResponseDto;
import school.sptech.CRUDBackend.dto.LoteItemEstoque.LoteItemEstoqueMapper;
import school.sptech.CRUDBackend.dto.LoteItemEstoque.LoteItemEstoqueRequestDto;
import school.sptech.CRUDBackend.dto.LoteItemEstoque.LoteItemEstoqueResponseDto;
import school.sptech.CRUDBackend.entity.Lote;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;
import school.sptech.CRUDBackend.service.LoteService;

import java.util.List;

@Tag(name = "Lote", description = "Operações CRUD relacionadas aos lotes de entrada dos Itens.")
@RestController
@RequestMapping("/lotes")
@RequiredArgsConstructor
public class LoteController {

    private final LoteService service;

    @Operation(
            summary = "Cadastro de um novo Lote.",
            description = "Retorna um objeto do tipo LoteResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lote cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "A descrição de Lote informada já existe no sistema.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo LoteRequestDto para cadastro.",
            required = true
    )
    @PostMapping
    public ResponseEntity<LoteResponseDto> cadastrar(
            @RequestBody LoteRequestDto loteParaCadastrar
    ){
        Lote lote = LoteMapper.toEntity(loteParaCadastrar);
        LoteResponseDto loteCadastrado = LoteMapper.toResponseDto(service.cadastrarLote(lote));

        return ResponseEntity.status(201).body(loteCadastrado);
    }

    @Operation(summary = "Listagem de todos os Lotes.", description = "Retorna uma lista de LoteResponseDto com todos os Itens no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui itens de estoque."),
            @ApiResponse(responseCode = "204", description = "Lista de itens está vazia"),
    })
    @GetMapping
    public ResponseEntity<List<LoteResponseDto>> listarTodos(){
        List<LoteResponseDto> todosOsLotes = service
                .listarTodosOsLotes()
                .stream().map(LoteMapper::toResponseDto)
                .toList();
        if (todosOsLotes.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(todosOsLotes);
    }

    @Operation(summary = "Exibição de um Lote por ID", description = "Retorna um objeto LoteResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<LoteResponseDto> buscarPorId(@PathVariable Integer id){
        LoteResponseDto loteEncontrado = LoteMapper.toResponseDto(
                service.buscarLotePorId(id));
        return ResponseEntity.status(200).body(loteEncontrado);
    }

    @Operation(summary = "Atualização de Lote.", description = "Retorna um objeto LoteResponseDto atualizado com os valores de um LoteRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo LoteRequestDto com valores de atualização.",
            required = true
    )
    @PutMapping("/{id}")
    public ResponseEntity<LoteResponseDto> atualizarPorId(@PathVariable
                                                                     Integer id, @RequestBody
                                                                     LoteRequestDto loteAtualizar){
        Lote loteParaAtualizar = LoteMapper.toEntity(loteAtualizar);
        LoteResponseDto loteAtualizado = LoteMapper.toResponseDto(service.atualizarLotePorId(id, loteParaAtualizar));

        return ResponseEntity.status(200).body(loteAtualizado);
    }

    @Operation(summary = "Deleção de um registro de Lote.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id){

        service.removerPorId(id);
        return ResponseEntity.status(200).build();
    }
}
