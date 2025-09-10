package school.sptech.CleanArchitecture.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CleanArchitecture.core.application.command.parceiro.AtualizarParceiroCommand;
import school.sptech.CleanArchitecture.core.application.command.parceiro.CriarParceiroCommand;
import school.sptech.CleanArchitecture.core.application.usecase.parceiro.*;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro.ParceiroEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro.ParceiroEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.parceiro.ParceiroMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.parceiro.ParceiroRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.parceiro.ParceiroResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Parceiro Controller", description = "Operações CRUD relacionadas aos parceiros.")
@RestController
@RequestMapping("/parceiros")
@RequiredArgsConstructor
public class ParceiroController {

    private final CadastrarParceiroUseCase cadastrarParceiroUseCase;
    private final ListarTodosParceirosUseCase listarTodosParceirosUseCase;
    private final BuscarParceiroPorNomeUseCase buscarParceiroPorNomeUseCase;
    private final AtualizarParceiroPorIdUseCase atualizarParceiroPorIdUseCase;
    private final RemoverParceiroPorIdUseCase removerParceiroPorIdUseCase;

    @Operation(
            summary = "Cadastro de um novo Parceiro.",
            description = "Retorna um objeto do tipo ParceiroResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Parceiro cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "O email de Parceiro informado já existe no sistema.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ParceiroRequestDto para cadastro.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<ParceiroResponseDto> cadastrar(@RequestBody @Valid ParceiroRequestDto parceiroParaCadastrar) {
        CriarParceiroCommand command = ParceiroMapper.toCriarCommand(parceiroParaCadastrar);
        Parceiro parceiro = cadastrarParceiroUseCase.executar(command);
        ParceiroEntity entity = ParceiroEntityMapper.ofDomain(parceiro);
        ParceiroResponseDto parceiroCadastrado = ParceiroMapper.toResponseDto(entity);
        return ResponseEntity.status(201).body(parceiroCadastrado);
    }

    @Operation(summary = "Listagem de todos os Parceiros.", description = "Retorna uma lista de ParceiroResponseDto com todos os Parceiros no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Parceiros."),
            @ApiResponse(responseCode = "204", description = "Lista de Parceiros está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/listagem/{categoria}")
    public ResponseEntity<List<ParceiroResponseDto>> listarTodos(@PathVariable String categoria) {
        List<ParceiroEntity> todosParceiros = listarTodosParceirosUseCase.execute(categoria)
                .stream()
                .map(ParceiroEntityMapper::ofDomain)
                .collect(Collectors.toList());

        List<ParceiroResponseDto> response = todosParceiros
                .stream()
                .map(ParceiroMapper::toResponseDto)
                .collect(Collectors.toList());

        return response.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Exibição de um Parceiro por nome", description = "Retorna um objeto ParceiroResponseDto de acordo com o nome informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o nome passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{categoria}/nome")
    public ResponseEntity<List<ParceiroResponseDto>> buscarPorNome(
            @PathVariable String categoria, @RequestParam String nome) {
        List<ParceiroEntity> parceiroEncontrado = ParceiroEntityMapper.ofDomains(
                buscarParceiroPorNomeUseCase.executar(categoria, nome));
        List<ParceiroResponseDto> response = ParceiroMapper.toResponseDtos(parceiroEncontrado);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Atualização de Parceiros.", description = "Retorna um objeto ParceiroResponseDto atualizado com os valores de um ParceiroRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo ParceiroRequestDto com valores de atualização.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<ParceiroResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid ParceiroRequestDto parceiroAtualizar) {
        AtualizarParceiroCommand command = ParceiroMapper.toAtualizarCommand(id, parceiroAtualizar);
        ParceiroEntity parceiroEncontrado = ParceiroEntityMapper.ofDomain(
                atualizarParceiroPorIdUseCase.executar(command));
        ParceiroResponseDto response = ParceiroMapper.toResponseDto(parceiroEncontrado);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Deleção de um registro de Parceiro.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        removerParceiroPorIdUseCase.executar(id);
        return ResponseEntity.status(204).build();
    }
}
