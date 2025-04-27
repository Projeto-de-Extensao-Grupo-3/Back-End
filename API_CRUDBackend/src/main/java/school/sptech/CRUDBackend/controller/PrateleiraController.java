package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.Prateleira.PrateleiraMapper;
import school.sptech.CRUDBackend.dto.Prateleira.PrateleiraRequestDto;
import school.sptech.CRUDBackend.dto.Prateleira.PrateleiraResponseDto;
import school.sptech.CRUDBackend.entity.Prateleira;
import school.sptech.CRUDBackend.service.PrateleiraService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Prateleria Controller", description = "Operações CRUD relacionadas as prateleiras de armazenamento de produto.")
@RestController
@RequestMapping("/prateleiras")
public class PrateleiraController {

    private final PrateleiraService service;

    public PrateleiraController(PrateleiraService service) {
        this.service = service;
    }

    @Operation(
            summary = "Cadastro de uma nova Prateleira.",
            description = "Retorna um objeto do tipo PrateleiraResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prateleira cadastrada com sucesso."),
            @ApiResponse(responseCode = "409", description = "O código de Prateleira informada já existe no sistema.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo PrateleiraRequestDto para cadastro.",
            required = true
    )
    @PostMapping
    public ResponseEntity<PrateleiraResponseDto> cadastrar(@RequestBody PrateleiraRequestDto prateleiraParaCadastrar) {
        Prateleira prateleira = PrateleiraMapper.toEntity(prateleiraParaCadastrar);
        PrateleiraResponseDto prateleiraCadastrada = PrateleiraMapper.toResponseDto(service.cadastrarPrateleira(prateleira));
        return ResponseEntity.status(201).body(prateleiraCadastrada);
    }

    @Operation(summary = "Listagem de todas as Prateleiras.", description = "Retorna uma lista de PrateleiraResponseDto com todas as Prateleiras no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Prateleiras."),
            @ApiResponse(responseCode = "204", description = "Lista de Prateleiras está vazia"),
    })
    @GetMapping
    public ResponseEntity<List<PrateleiraResponseDto>> listarTodas() {
        List<PrateleiraResponseDto> todasPrateleiras = service.listarTodos()
                .stream()
                .map(PrateleiraMapper::toResponseDto)
                .collect(Collectors.toList());

        if (todasPrateleiras.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(todasPrateleiras);
    }

    @Operation(summary = "Exibição de uma Prateleira por ID", description = "Retorna um objeto PrateleiraResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<PrateleiraResponseDto> buscarPorId(@PathVariable Integer id) {
        PrateleiraResponseDto prateleiraEncontrada = PrateleiraMapper.toResponseDto(service.buscarPorId(id));
        return ResponseEntity.status(200).body(prateleiraEncontrada);
    }

    @Operation(summary = "Exibição de uma Prateleira por nome", description = "Retorna um objeto PrateleiraResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<PrateleiraResponseDto> buscarPorCodigo(@PathVariable String codigo) {
        PrateleiraResponseDto prateleiraEncontrada = PrateleiraMapper.toResponseDto(service.buscarPorCodigo(codigo));
        return ResponseEntity.status(200).body(prateleiraEncontrada);
    }

    @Operation(summary = "Atualização de Prateleria.", description = "Retorna um objeto PrateleiraResponseDto atualizado com os valores de um PrateleiraRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo CategoriaRequestDto com valores de atualização.",
            required = true
    )
    @PutMapping("/{id}")
    public ResponseEntity<PrateleiraResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid PrateleiraRequestDto prateleiraAtualizar){
        Prateleira prateleiraParaAtualizar = PrateleiraMapper.toEntity(prateleiraAtualizar);
        PrateleiraResponseDto prateleiraAtualizada = PrateleiraMapper.toResponseDto(service.atualizarPorId(id, prateleiraParaAtualizar));
        return ResponseEntity.status(200).body(prateleiraAtualizada);
    }

    @Operation(summary = "Deleção de um registro de Prateleira  .", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        service.removerPorId(id);
        return ResponseEntity.status(200).build();
    }

}
