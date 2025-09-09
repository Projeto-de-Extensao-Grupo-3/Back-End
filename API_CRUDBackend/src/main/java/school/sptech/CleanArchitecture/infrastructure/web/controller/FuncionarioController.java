
package school.sptech.CleanArchitecture.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.CadastrarFuncionarioUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario.FuncionarioMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario.FuncionarioRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario.FuncionarioResponseDto;

@Tag(name = "Funcionários", description = "Cadastro e listagem de funcionários")
@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final CadastrarFuncionarioUseCase cadastrarFuncionarioUseCase;

    @Operation(summary = "Cadastrar Funcionário")
    @PostMapping
    public ResponseEntity<FuncionarioResponseDto> cadastrar(@RequestBody @Valid FuncionarioRequestDto dto) {
        Funcionario funcionario = cadastrarFuncionarioUseCase.executar(FuncionarioMapper.toCriarCommand(dto));
        return ResponseEntity.status(201).body(FuncionarioMapper.toResponseDto(funcionario));
    }
}
