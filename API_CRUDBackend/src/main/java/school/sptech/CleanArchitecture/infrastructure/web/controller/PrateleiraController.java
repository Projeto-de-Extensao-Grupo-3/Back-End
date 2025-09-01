package school.sptech.CleanArchitecture.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CleanArchitecture.core.application.command.prateleira.CriarPrateleiraCommand;
import school.sptech.CleanArchitecture.core.application.usecase.prateleira.*;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira.PrateleiraEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.Prateleira.PrateleiraMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.Prateleira.PrateleiraResponseDto;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Prateleria Controller", description = "Operações CRUD relacionadas as prateleiras de armazenamento de produto.")
@RestController
@RequestMapping("/prateleiras2")
public class PrateleiraController {

    private final CriarPrateleiraUseCase criarPrateleiraUseCase;

    private final PrateleiraListarAllUseCase prateleiraListarAllUseCase;

    private final PrateleiraBuscarPorIdUseCase prateleiraBuscarPorIdUseCase;

    private final PrateleiraBuscarPorCodigoUseCase prateleiraBuscarPorCodigoUseCase;

    private final PrateleiraAtualizarPorIdUseCase prateleiraAtualizarPorIdUseCase;

    private final PrateleiraRemoverPorIdUseCase prateleiraRemoverPorIdUseCase;

    public PrateleiraController(CriarPrateleiraUseCase criarPrateleiraUseCase, PrateleiraListarAllUseCase prateleiraListarAllUseCase, PrateleiraBuscarPorIdUseCase prateleiraBuscarPorIdUseCase, PrateleiraBuscarPorCodigoUseCase prateleiraBuscarPorCodigoUseCase, PrateleiraAtualizarPorIdUseCase prateleiraAtualizarPorIdUseCase, PrateleiraRemoverPorIdUseCase prateleiraRemoverPorIdUseCase) {
        this.criarPrateleiraUseCase = criarPrateleiraUseCase;
        this.prateleiraListarAllUseCase = prateleiraListarAllUseCase;
        this.prateleiraBuscarPorIdUseCase = prateleiraBuscarPorIdUseCase;
        this.prateleiraBuscarPorCodigoUseCase = prateleiraBuscarPorCodigoUseCase;
        this.prateleiraAtualizarPorIdUseCase = prateleiraAtualizarPorIdUseCase;
        this.prateleiraRemoverPorIdUseCase = prateleiraRemoverPorIdUseCase;
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
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<PrateleiraResponseDto> cadastrar(@RequestBody CriarPrateleiraCommand command) {
        Prateleira prateleira = criarPrateleiraUseCase.executar(command);
        PrateleiraResponseDto prateleiraResponseDto = PrateleiraMapper.toResponseDto(prateleira);
        return ResponseEntity.status(201).body(prateleiraResponseDto);
    }

    @Operation(summary = "Listagem de todas as Prateleiras.", description = "Retorna uma lista de PrateleiraResponseDto com todas as Prateleiras no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Prateleiras."),
            @ApiResponse(responseCode = "204", description = "Lista de Prateleiras está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<PrateleiraResponseDto>> listarTodas() {
        List<PrateleiraResponseDto> todasPrateleiras = prateleiraListarAllUseCase.executar()
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
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<PrateleiraResponseDto> buscarPorId(@PathVariable Integer id) {
        PrateleiraResponseDto prateleiraEncontrada = PrateleiraMapper.toResponseDto(prateleiraBuscarPorIdUseCase.executar(id));
        return ResponseEntity.status(200).body(prateleiraEncontrada);
    }

    @Operation(summary = "Exibição de uma Prateleira por nome", description = "Retorna um objeto PrateleiraResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{codigo}")
    public ResponseEntity<PrateleiraResponseDto> buscarPorCodigo(@PathVariable String codigo) {
        PrateleiraResponseDto prateleiraEncontrada = PrateleiraMapper.toResponseDto(prateleiraBuscarPorCodigoUseCase.executar(codigo));
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
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<PrateleiraResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid CriarPrateleiraCommand prateleiraAtualizar){
        Prateleira prateleiraAtualizada = prateleiraAtualizarPorIdUseCase.executar(id, prateleiraAtualizar);
        PrateleiraResponseDto responseDto = PrateleiraMapper.toResponseDto(prateleiraAtualizada);
             return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(summary = "Deleção de um registro de Prateleira  .", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        prateleiraRemoverPorIdUseCase.executar(id);
        return ResponseEntity.status(200).build();
    }
}
