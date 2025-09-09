
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
import school.sptech.CleanArchitecture.core.application.command.funcionario.LoginFuncionarioCommand;
import school.sptech.CleanArchitecture.core.application.usecase.funcionario.AutenticarFuncionarioUseCase;
import school.sptech.CleanArchitecture.infrastructure.web.dto.auth.LoginRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.auth.TokenResponseDto;

@Tag(name = "Auth", description = "Autenticação com JWT")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AutenticarFuncionarioUseCase autenticarFuncionarioUseCase;

    @Operation(summary = "Login", description = "Retorna token JWT")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginRequestDto dto) {
        String token = autenticarFuncionarioUseCase.executar(new LoginFuncionarioCommand(dto.getEmail(), dto.getSenha()));
        return ResponseEntity.ok(new TokenResponseDto(token));
    }
}
