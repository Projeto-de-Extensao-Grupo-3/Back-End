
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
import school.sptech.CleanArchitecture.core.application.command.funcionario.FuncionarioAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.command.funcionario.LoginFuncionarioCommand;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.*;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario.FuncionarioMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario.FuncionarioRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario.FuncionarioResponseDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario.FuncionarioTokenDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loginDto.FuncionarioLoginDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loginDto.FuncionarioLoginMapper;

import java.util.List;

@Tag(name = "Funcionários", description = "Cadastro e listagem de funcionários")
@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final CadastrarFuncionarioUseCase cadastrarFuncionarioUseCase;
    private final AutenticarFuncionarioUseCase autenticarFuncionarioUseCase;
    private final FuncionarioAtualizarPorIdUseCase funcionarioAtualizarPorIdUseCase;
    private final FuncionarioBuscarPorNomeUseCAse funcionarioBuscarPorNomeUseCAse;
    private final FuncionarioListAllUseCase funcionarioListAllUseCase;
    private final FuncionarioRemoverPorIdUseCase funcionarioRemoverPorIdUseCase;

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
    public ResponseEntity<FuncionarioResponseDto> cadastrar(@RequestBody @Valid FuncionarioRequestDto dto) {
        Funcionario funcionario = cadastrarFuncionarioUseCase.executar(FuncionarioMapper.toCriarCommand(dto));
        return ResponseEntity.status(201).body(FuncionarioMapper.toResponseDto(funcionario));
    }


    @Operation(
            summary = "* Login de funcionário.",
            description = "Retorna um objeto do tipo FuncionarioResponseDto e o token de acesso"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário logado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Funcionário inválido")
    })
    @PostMapping("/login")
    public ResponseEntity<FuncionarioTokenDto> login(@RequestBody FuncionarioLoginDto funcionarioLoginDto) {
        LoginFuncionarioCommand command = FuncionarioLoginMapper.toLoginCommand(funcionarioLoginDto);
        FuncionarioTokenDto funcionarioTokenDto = autenticarFuncionarioUseCase.executar(command);
        return ResponseEntity.status(200).body(funcionarioTokenDto);
    }

    @Operation(summary = "Listagem de todos os Funcionários.", description = "Retorna uma lista de FuncionarioResponseDto com todos os funcionários no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista possui funcionários."),
            @ApiResponse(responseCode = "204", description = "Lista de funcionários está vazia"),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDto>> verificarTodos() {
        List<FuncionarioResponseDto> todosFuncionarios = FuncionarioMapper.toResponseDtos(
                funcionarioListAllUseCase.execute()
        );
        if(todosFuncionarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(todosFuncionarios);
    }

    @Operation(summary = "Exibição de um funcionário por nome", description = "Retorna uma lista de objetos FuncionarioResponseDto de acordo com o ID informado na PathVariable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "204", description = "Nenhum registro com o nome passado no RequestParam foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/busca")
    public ResponseEntity<List<FuncionarioResponseDto>> buscarPorNome(@RequestParam String nome) {
        List<FuncionarioResponseDto> funcionarios = FuncionarioMapper.toResponseDtos(
                funcionarioBuscarPorNomeUseCAse.execute(nome)
        );
        if (funcionarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(funcionarios);
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
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDto> atualizarPorId (
            @PathVariable Integer id,
            @RequestBody @Valid FuncionarioRequestDto funcionarioAtualizar
    ) {
        FuncionarioAtualizarPorIdCommand funcionarioParaAtualizar = FuncionarioMapper.toAtualizarCommand(id, funcionarioAtualizar);
        FuncionarioResponseDto funcionarioAtualizado = FuncionarioMapper.toResponseDto(
                funcionarioAtualizarPorIdUseCase.execute(funcionarioParaAtualizar)
        );
        return ResponseEntity.status(200).body(funcionarioAtualizado);
    }

    @Operation(summary = "Deleção de um registro de Funcionário.", description = "Não possui retorno de corpo quando o registro é deletado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sem corpo de resposta, registro deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum registro com o ID passado no PathVariable foi encontrado."),
    })
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Integer id) {
        funcionarioRemoverPorIdUseCase.execute(id);
        return ResponseEntity.status(204).build();
    }

//    // Endpoint de teste para login sem token. Apagar após testes
//    @GetMapping("/login-teste")
//    public ResponseEntity<FuncionarioResponseDto> loginTeste(@RequestBody FuncionarioLoginDto funcionarioLogin) {
//        FuncionarioResponseDto funcionario = FuncionarioMapper.toResponseDto(
//                funcionarioService.loginTeste(funcionarioLogin.getEmail(), funcionarioLogin.getSenha())
//        );
//        return ResponseEntity.status(200).body(funcionario);
//    }

}
