package school.sptech.CleanArchitecture.infrastructure.web.controller;

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
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.SaidaEstoqueAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.saidaEstoque.SaidaEstoqueCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.saidaEstoque.SaidaEstoqueEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque.SaidaEstoqueRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque.SaidaEstoqueResponseDto;
import school.sptech.CleanArchitecture.infrastructure.web.rabbitmq.RabbitProducer;


import java.time.LocalDate;
import java.util.List;

@Tag(name = "Saída de Estoque Controller", description = "Operações CRUD relacionadas ás Saídas de Item do Estoque.")
@RestController
@RequestMapping("/saidas-estoque")
@RequiredArgsConstructor
public class SaidaEstoqueController {

    private final SaidaEstoqueAtualizarPorIdUseCase saidaEstoqueAtualizarPorIdUseCase;

    private final SaidaEstoqueBuscarPorDataUseCase saidaEstoqueBuscarPorDataUseCase;

    private final SaidaEstoqueBuscarPorIdUseCase saidaEstoqueBuscarPorIdUseCase;

    private final SaidaEstoqueBuscarPorMotivoUseCase saidaEstoqueBuscarPorMotivoUseCase;

    private final SaidaEstoqueCadastrarUseCase saidaEstoqueCadastrarUseCase;

    private final SaidaEstoqueListAllUseCAse saidaEstoqueListAllUseCAse;

    private final SaidaEstoqueRemoverPorIdUseCase saidaEstoqueRemoverPorIdUseCase;

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
        SaidaEstoqueCadastrarCommand command = SaidaEstoqueEntityMapper.toCadastrarCommand(saidaCadastro);
        SaidaEstoqueResponseDto responseDto = SaidaEstoqueEntityMapper
                .toResponseDto(saidaEstoqueCadastrarUseCase.execute(command));

        return ResponseEntity.status(201).body(responseDto);
    }

    @Operation(summary = "Listagem de todos as saídas.", description = "Retorna uma lista de SaidaEstoqueResponseDto com todos as Saídas do Estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui saídas."),
            @ApiResponse(responseCode = "204", description = "Lista de saídas está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<SaidaEstoqueResponseDto>> listarSaidas(){
        List<SaidaEstoqueResponseDto> todasAsSaidas = SaidaEstoqueEntityMapper.toResponseDtos(saidaEstoqueListAllUseCAse.execute());

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
        SaidaEstoqueResponseDto saidaEncontrada = SaidaEstoqueEntityMapper.toResponseDto(saidaEstoqueBuscarPorIdUseCase.execute(id));

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
        List<SaidaEstoqueResponseDto> saidasEncontradas = SaidaEstoqueEntityMapper.toResponseDtos(saidaEstoqueBuscarPorMotivoUseCase.execute(motivo));

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
        List<SaidaEstoqueResponseDto> saidasEncontradas = SaidaEstoqueEntityMapper.toResponseDtos(saidaEstoqueBuscarPorDataUseCase.execute(data));

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
        SaidaEstoqueAtualizarPorIdCommand command = SaidaEstoqueEntityMapper.toAtualizarCommand(id, saidaParaAtualizar);
        SaidaEstoqueResponseDto responseDto = SaidaEstoqueEntityMapper.toResponseDto(
                saidaEstoqueAtualizarPorIdUseCase.execute(command));
        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(summary = "Deleção de um registro de Saída de Estoque.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSaida(@PathVariable Integer id) {
        saidaEstoqueRemoverPorIdUseCase.execute(id);
        return ResponseEntity.status(204).build();
    }
}
