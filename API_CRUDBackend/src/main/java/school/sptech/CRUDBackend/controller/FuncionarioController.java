package school.sptech.CRUDBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.CRUDBackend.dto.funcionario.FuncionarioMapper;
import school.sptech.CRUDBackend.dto.funcionario.FuncionarioRequestDto;
import school.sptech.CRUDBackend.dto.funcionario.FuncionarioResponseDto;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.service.FuncionarioService;

import java.util.List;

@Tag(name = "Funcionários", description = "Operações CRUD relacionadas funcionários..")
@RestController
@RequestMapping("/funcionarios")
@Setter
public class FuncionarioController {

    private FuncionarioService funcionarioService;


    @Operation(
            summary = "Cadastro de funcionário.",
            description = "Retorna um objeto do tipo FuncionarioResponseDto quando cadastrado com sucesso."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "O CPF e Email informados já existem no sistema.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo FuncionarioRequestDto para cadastro.",
            required = true
    )
    @PostMapping
    public ResponseEntity<FuncionarioResponseDto> cadastrar(
            @RequestBody @Valid FuncionarioRequestDto funcionarioCad
    ) {
        Funcionario funcionarioParaCadastrar = FuncionarioMapper.toEntity(funcionarioCad);
        FuncionarioResponseDto funcionarioCadastrado = FuncionarioMapper.toResponseDto(
                funcionarioService.cadastrarFuncionario(funcionarioParaCadastrar)
        );
        return ResponseEntity.status(201).body(funcionarioCadastrado);
    }

    @Operation(summary = "Listagem de todos os Funcionários.", description = "Retorna uma lista de FuncionarioResponseDto com todos os funcionários no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui funcionários."),
            @ApiResponse(responseCode = "204", description = "Lista de funcionários está vazia"),
    })
    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDto>> verificarTodos() {
        List<FuncionarioResponseDto> todosFuncionarios = FuncionarioMapper.toResponseDtos(
                funcionarioService.verificarTodosFuncionarios()
        );
        if(todosFuncionarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(todosFuncionarios);
    }

    @Operation(summary = "Exibição de um funcionário por ID", description = "Retorna um objeto FuncionarioResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> buscarPorId(@PathVariable Integer id) {
        FuncionarioResponseDto funcionario = FuncionarioMapper.toResponseDto(
                funcionarioService.buscarFuncionarioPorId(id)
        );
        return ResponseEntity.status(200).body(funcionario);
    }

    @Operation(summary = "Atualização de Funcionário.", description = "Retorna um objeto FuncionarioResponseDto atualizado com os valores de um FuncionarioRequestDto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto do tipo FuncionarioRequestDto com valores de atualização.",
            required = true
    )
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> atualizarPorId (
            @PathVariable Integer id,
            @RequestBody @Valid FuncionarioRequestDto funcionarioAtualizar
    ) {
        Funcionario funcionarioParaAtualizar = FuncionarioMapper.toEntity(funcionarioAtualizar);
        FuncionarioResponseDto funcionarioAtualizado = FuncionarioMapper.toResponseDto(
                funcionarioService.atualizarFuncionarioPorId(id, funcionarioParaAtualizar)
        );
        return ResponseEntity.status(200).body(funcionarioAtualizado);
    }

    @Operation(summary = "Deleção de um registro de Funcionário.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        funcionarioService.removerFuncionarioPorId(id);
        return ResponseEntity.status(204).build();
    }
}
