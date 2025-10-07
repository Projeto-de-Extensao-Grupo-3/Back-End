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
import school.sptech.CleanArchitecture.core.application.command.corteTecido.AtualizarCorteTecidoCommand;
import school.sptech.CleanArchitecture.core.application.command.corteTecido.CriarCorteTecidoCommand;
import school.sptech.CleanArchitecture.core.application.usecase.corteTecido.*;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.BuscarFuncionarioPorIdUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.corteTecido.CorteTecidoEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.corteTecido.CorteTecidoEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.corteTecido.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Cortes de Tecidos Controller", description = "Operações CRUD relacionadas aos cortes de tecidos (data de inicio, término do corte e quem efetuou).")
@RestController
@RequestMapping("/cortes-tecido")
@RequiredArgsConstructor
public class CorteTecidoController {
    private final AtualizarCorteTecidoPorIdUseCase atualizarCorteTecidoPorIdUseCase;
    private final BuscarCorteTecidoPorIdUseCase buscarCorteTecidoPorIdUseCase;
    private final CadastrarCorteTecidoUseCase cadastrarCorteTecidoUseCase;
    private final DeletarCorteTecidoPorIdUseCase deletarCorteTecidoPorIdUseCase;
    private final ListarTodosCorteTecidoUseCase listarTodosCorteTecidoUseCase;
    private final BuscarFuncionarioPorIdUseCase buscarFuncionarioPorIdUseCase;

    @Operation(
            summary = "Cadastramento de um novo corte de tecido.",
            description = "Retorna um objeto do tipo CorteTecidoResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Corte cadastrado com sucesso.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo CorteTecidoRequestDto para cadastro.",
            required = true
    )
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<CorteTecidoCadastrarResponseDto> cadastrar(
            @RequestBody @Valid CorteTecidoRequestDto corteTecido
    ) {
        CriarCorteTecidoCommand command = CorteTecidoMapper.toCriarCommand(corteTecido);
        CorteTecido corteParaCadastrar = cadastrarCorteTecidoUseCase.executar(command);
        CorteTecidoEntity entity = CorteTecidoEntityMapper.ofDomainCadsatrar(corteParaCadastrar);
        CorteTecidoCadastrarResponseDto corteTecidoCadastrado = CorteTecidoMapper.toCadastroResponseDto(entity);

        Funcionario funcionarioEncontrado = buscarFuncionarioPorIdUseCase.execute(command.funcionario());
        String nomeFuncionario = funcionarioEncontrado.getNome();
        String emailFuncionario = funcionarioEncontrado.getEmail().getValue();
        String telefoneFuncionario = funcionarioEncontrado.getTelefone().getValue();

        corteTecidoCadastrado.setNomeFuncionario(nomeFuncionario);
        corteTecidoCadastrado.setEmail(emailFuncionario);
        corteTecidoCadastrado.setTelefone(telefoneFuncionario);

        return ResponseEntity.status(201).body(corteTecidoCadastrado);
    }


    @Operation(summary = "Listagem de todos os cortes de tecidos no sistema.", description = "Retorna uma lista de CorteTecidoResponseDtoa com todos os cortes de tecido no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "lista possui cortes de tecido"),
            @ApiResponse(responseCode = "204", description = "lista de cortes está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<CorteTecidoResponseDto>> listarTodos() {
        List<CorteTecidoEntity> cortesTecido = listarTodosCorteTecidoUseCase.execute()
                .stream()
                .map(CorteTecidoEntityMapper::ofDomainCadsatrar)
                .toList();

        List<CorteTecidoResponseDto> cortesTecidoResponseDtos = cortesTecido
                .stream()
                .map(CorteTecidoMapper::toResponseDto)
                .toList();

        return ResponseEntity.status(200).body(cortesTecidoResponseDtos);
    }


    @Operation(summary = "Exibição de um corte de tecido por ID", description = "Retorna um objeto CorteTecidoResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID informado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<CorteTecidoResponseDto> buscarPorId(@PathVariable Integer id) {
        CorteTecidoEntity corteTecido = CorteTecidoEntityMapper.ofDomainCadsatrar(
                buscarCorteTecidoPorIdUseCase.executar(id));
        CorteTecidoResponseDto response = CorteTecidoMapper.toResponseDto(corteTecido);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Atualização de Corte de Tecido.", description = "Retorna um objeto CorteTecidoResponseDto atualizado com os valores de um CorteTecidoRequestDto.")
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
    public ResponseEntity<CorteTecidoResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid CorteTecidoRequestDto corteTecido
    ) {
        AtualizarCorteTecidoCommand command = CorteTecidoMapper.toAtualizarCommand(id, corteTecido);
        CorteTecidoEntity corteTecidoAtualizado = CorteTecidoEntityMapper.ofDomainCadsatrar(
                atualizarCorteTecidoPorIdUseCase.executar(command));
        CorteTecidoResponseDto response = CorteTecidoMapper.toResponseDto(corteTecidoAtualizado);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Deleção de um registro de corte de tecido.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID informado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarCorteTecidoPorIdUseCase.executar(id);
        return ResponseEntity.status(204).build();
    }
}
