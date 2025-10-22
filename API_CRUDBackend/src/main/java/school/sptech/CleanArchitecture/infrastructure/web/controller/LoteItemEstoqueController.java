package school.sptech.CleanArchitecture.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.AtualizarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.application.command.loteItemEstoque.CriarLoteItemEstoqueCommand;
import school.sptech.CleanArchitecture.core.application.usecase.loteItemEstoque.*;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.*;

import java.util.List;

@Tag(name = "* Entrada de Item Estoque Controller", description = "Operações CRUD relacionadas aos itens que chegaram em um lote.")
@RestController
@RequestMapping("/lotes-item-estoque")
@RequiredArgsConstructor
public class LoteItemEstoqueController {

    private final AtualizarPorIdloteItemEstoqueUseCase atualizarPorIdloteItemEstoqueUseCase;
    private final BuscarPorIdLoteItemEstoqueUseCase buscarPorIdLoteItemEstoqueUseCase;
    private final BuscarTodosLoteItemEstoqueUseCase buscarTodosLoteItemEstoqueUseCase;
    private final CadastrarLoteItemEstoqueUseCase cadastrarLoteItemEstoqueUseCase;
    private final RemoverLoteItemEstoqueUseCase removerLoteItemEstoqueUseCase;
    private final BuscarLotesPaginadoUseCase buscarLotesPaginadoUseCase;
    private final BuscarLotesPaginadoSaidaUseCase buscarLotesPaginadoSaidaUseCase;

    @Operation(
            summary = "* Cadastro de um novo Lote de Item.",
            description = "Retorna um objeto do tipo LoteItemEstoqueResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lote de Item cadastrado com sucesso.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo LoteItemEstoqueRequestDto para cadastro.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<LoteItemEstoqueResponseDto> cadastrar(
            @RequestBody @Valid LoteItemEstoqueRequestDto loteItemEstoqueCadastrar
    ) {
        CriarLoteItemEstoqueCommand command = LoteItemEstoqueMapper.toCriarCommand(loteItemEstoqueCadastrar);
        LoteItemEstoque loteItemEstoque = cadastrarLoteItemEstoqueUseCase.executar(command);
        LoteItemEstoqueEntity entity = LoteItemEstoqueEntityMapper.ofDomain(loteItemEstoque);
        LoteItemEstoqueResponseDto loteItemEstoqueCadastrado = LoteItemEstoqueMapper.toResponseDto(entity);
        return ResponseEntity.status(201).body(loteItemEstoqueCadastrado);
    }

    @Operation(summary = "Listagem de todos lotes de itens no sistema.", description = "Retorna uma lista de LoteItemEstoqueResponseDto com todos os lotes de itens no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "lista possui Lotes de Itens"),
            @ApiResponse(responseCode = "204", description = "lista de Lotes está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<LoteItemEstoqueResponseDto>> listarTodos(){
        List<LoteItemEstoqueEntity> todosOsLoteItemEstoque = buscarTodosLoteItemEstoqueUseCase.execute()
                .stream()
                .map(LoteItemEstoqueEntityMapper::ofDomain)
                .toList();

        List<LoteItemEstoqueResponseDto> response = todosOsLoteItemEstoque
                .stream()
                .map(LoteItemEstoqueMapper::toResponseDto)
                .toList();

        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Exibição de um Lote de Item por ID", description = "Retorna um objeto LoteItemEstoqueResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID informado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<LoteItemEstoqueResponseDto> buscarPorId(@PathVariable Integer id){
        LoteItemEstoqueEntity loteItemEstoqueEncontrado = LoteItemEstoqueEntityMapper.ofDomain(
                buscarPorIdLoteItemEstoqueUseCase.executar(id));
        LoteItemEstoqueResponseDto response = LoteItemEstoqueMapper.toResponseDto(loteItemEstoqueEncontrado);
        return ResponseEntity.status(200).body(response);
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
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<LoteItemEstoqueResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid LoteItemEstoqueRequestDto loteItemEstoqueCadastrar
    ) {
        AtualizarLoteItemEstoqueCommand command = LoteItemEstoqueMapper.toAtualizarCommand(id, loteItemEstoqueCadastrar);
        LoteItemEstoqueEntity loteItemEstoqueAtualizado = LoteItemEstoqueEntityMapper.ofDomain(
                atualizarPorIdloteItemEstoqueUseCase.executar(command));
        LoteItemEstoqueResponseDto response  = LoteItemEstoqueMapper.toResponseDto(loteItemEstoqueAtualizado);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Deleção de um registro de lote de item.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote de Item deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID informado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(
            @PathVariable Integer id)
    {
        removerLoteItemEstoqueUseCase.executar(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/paginado")
    public PaginacaoResponseDTO<EntradaPaginacaoDTO> buscarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return buscarLotesPaginadoUseCase.executar(page, limit);
    }

    @GetMapping("/paginadoSaida")
    public PaginacaoResponseDTO<SaidaPaginacaoDTO> buscarPaginadoSaida(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return buscarLotesPaginadoSaidaUseCase.executar(page, limit);
    }
}
