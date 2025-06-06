package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.SaidaEstoque.SaidaEstoqueMapper;
import school.sptech.CRUDBackend.dto.SaidaEstoque.SaidaEstoqueRequestDto;
import school.sptech.CRUDBackend.dto.SaidaEstoque.SaidaEstoqueResponseDto;
import school.sptech.CRUDBackend.entity.SaidaEstoque;
import school.sptech.CRUDBackend.service.SaidaEstoqueService;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Saída de Estoque Controller", description = "Operações CRUD relacionadas ás Saídas de Item do Estoque.")
@RestController
@RequestMapping("/saidas-estoque")
@RequiredArgsConstructor
public class SaidaEstoqueController {

    private final SaidaEstoqueService service;

    @Operation(
            summary = "Cadastramento de uma nova saída.",
            description = "Retorna um objeto do tipo SaidaEstoqueResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Saída de Estoque cadastrado com sucesso.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo SaidaEstoqueRequestDto para cadastro.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<SaidaEstoqueResponseDto> cadastrarSaida(@RequestBody @Valid SaidaEstoqueRequestDto saidaCadastro){
        SaidaEstoque saidaParaCadastrar = SaidaEstoqueMapper.toEntity(saidaCadastro);
        SaidaEstoqueResponseDto saidaCadastrada = SaidaEstoqueMapper
                .toResponseDto(service
                .cadastrar(saidaParaCadastrar));
        return ResponseEntity.status(201).body(saidaCadastrada);
    }

    @Operation(summary = "Listagem de todos as saídas.", description = "Retorna uma lista de SaidaEstoqueResponseDto com todos as Saídas do Estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui saídas."),
            @ApiResponse(responseCode = "204", description = "Lista de saídas está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<SaidaEstoqueResponseDto>> listarSaidas(){
        List<SaidaEstoqueResponseDto> todasAsSaidas = SaidaEstoqueMapper.toResponseDtos(service.listar());

        return todasAsSaidas.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(todasAsSaidas);
    }

    @Operation(summary = "Exibição de uma Saída por ID", description = "Retorna um objeto SaidaEstoqueResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<SaidaEstoqueResponseDto> buscarPorId(@PathVariable Integer id){
        SaidaEstoqueResponseDto saidaEncontrada = SaidaEstoqueMapper.toResponseDto(service.buscarPorId(id));

        return ResponseEntity.status(200).body(saidaEncontrada);
    }

    @Operation(summary = "Listagem de saídas por motivo.", description = "Retorna uma lista de SaidaEstoqueResponseDto com o motivo de saída passado no RequestParam.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui registros de saída."),
            @ApiResponse(responseCode = "404", description = "A lista não possui nenhuma saída com o motivo passado"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/motivo")
    public ResponseEntity<List<SaidaEstoqueResponseDto>> buscarPorMotivo(@RequestParam String motivo){
        List<SaidaEstoqueResponseDto> saidasEncontradas = SaidaEstoqueMapper.toResponseDtos(service.buscarPorMotivo(motivo));

        return ResponseEntity.status(200).body(saidasEncontradas);
    }

    @Operation(summary = "Listagem de saídas por data.", description = "Retorna uma lista de SaidaEstoqueResponseDto com a data de saída passado no RequestParam.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui registros de saída."),
            @ApiResponse(responseCode = "404", description = "A lista não possui nenhuma saída na data passada"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/data")
    public ResponseEntity<List<SaidaEstoqueResponseDto>> buscarPorData(@PastOrPresent @RequestParam LocalDate data){
        List<SaidaEstoqueResponseDto> saidasEncontradas = SaidaEstoqueMapper.toResponseDtos(service.buscarPorData(data));

        return ResponseEntity.status(200).body(saidasEncontradas);
    }

    @Operation(summary = "Atualização de Saída.", description = "Retorna um objeto SaidaEstoqueResponseDto atualizado com os valores de um SaidaEstoqueRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo PermissaoRequestDto com valores de atualização.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<SaidaEstoqueResponseDto> atualizarSaida(
            @PathVariable Integer id,
            @RequestBody SaidaEstoqueRequestDto saidaParaAtualizar
    ) {
        SaidaEstoque saida = SaidaEstoqueMapper.toEntity(saidaParaAtualizar);
        SaidaEstoqueResponseDto saidaAtualizada = SaidaEstoqueMapper.toResponseDto(
                service.atualizarPorId(id, saida)
        );
        return ResponseEntity.status(200).body(saidaAtualizada);
    }

    @Operation(summary = "Deleção de um registro de Saída de Estoque.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSaida(@PathVariable Integer id) {
        service.removerPorId(id);
        return ResponseEntity.status(204).build();
    }
}
