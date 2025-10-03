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
import school.sptech.CleanArchitecture.core.application.command.lote.AtualizarLotePorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.lote.CriarLoteCommand;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.BuscarFuncionarioPorIdUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.lote.*;
import school.sptech.CleanArchitecture.core.application.usecase.parceiro.BuscarParceiroPorIdUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.lote.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Lote Controller", description = "Operações CRUD relacionadas aos lotes de entrada dos Itens.")
@RestController
@RequestMapping("/lotes")
@RequiredArgsConstructor
public class LoteController {

    private final AtualizarLotePorIdUseCase atualizarLotePorIdUseCase;
    private final BuscarLotePorIdUseCase buscarLotePorIdUseCase;
    private final CadastrarLoteUseCase cadastrarLoteUseCase;
    private final ListarTodosLotesUseCase listarTodosLotesUseCase;
    private final RemoverLotePorIdUseCase removerLotePorIdUseCase;
    private final BuscarParceiroPorIdUseCase buscarParceiroPorIdUseCase;
    private final BuscarFuncionarioPorIdUseCase buscarFuncionarioPorIdUseCase;

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
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<LoteResponseDto> cadastrar(
            @RequestBody @Valid LoteRequestDto loteParaCadastrar
    ){
        CriarLoteCommand command = LoteMapper.toCriarCommand(loteParaCadastrar);
        Lote lote = cadastrarLoteUseCase.executar(command);
        LoteEntity entity = LoteEntityMapper.ofDomain(lote);
        LoteResponseDto loteCadastrado = LoteMapper.toResponseDto(entity);
        return ResponseEntity.status(201).body(loteCadastrado);
    }

    @Operation(summary = "Listagem de todos os Lotes.", description = "Retorna uma lista de LoteResponseDto com todos os Itens no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui itens de estoque."),
            @ApiResponse(responseCode = "204", description = "Lista de itens está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<LoteResponseDto>> listarTodos(){
        List<LoteEntity> todosOsLotes = listarTodosLotesUseCase.execute()
                .stream()
                .map(LoteEntityMapper::ofDomain)
                .toList();

        List<LoteResponseDto> response = todosOsLotes
                .stream()
                .map(LoteMapper::toResponseDto)
                .toList();

        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Exibição de um Lote por ID", description = "Retorna um objeto LoteResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<LoteResponseDto> buscarPorId(@PathVariable Integer id){
      LoteEntity lote = LoteEntityMapper.ofDomain(
              buscarLotePorIdUseCase.executar(id));
      LoteResponseDto response = LoteMapper.toResponseDto(lote);
      return ResponseEntity.status(200).body(response);
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
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<LoteResponseDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid LoteRequestDto loteAtualizar){
        AtualizarLotePorIdCommand command = LoteMapper.toAtualizarCommand(id, loteAtualizar);
        LoteEntity loteAtualizado = LoteEntityMapper.ofDomain(atualizarLotePorIdUseCase.executar(command));
        LoteResponseDto response = LoteMapper.toResponseDto(loteAtualizado);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Deleção de um registro de Lote.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id){

        removerLotePorIdUseCase.executar(id);
        return ResponseEntity.status(200).build();
    }
}