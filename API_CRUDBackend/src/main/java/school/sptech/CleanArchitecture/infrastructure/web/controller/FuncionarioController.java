
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
import school.sptech.CleanArchitecture.core.application.command.funcionario.LoginFuncionarioCommand;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.AutenticarFuncionarioUseCase;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.CadastrarFuncionarioUseCase;
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



}
