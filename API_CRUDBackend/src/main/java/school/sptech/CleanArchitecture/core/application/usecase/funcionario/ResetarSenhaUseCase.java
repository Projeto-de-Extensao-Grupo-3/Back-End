package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.CRUDBackend.config.GerenciadorTokenJwt;
import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.funcionario.FuncionarioNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

import java.util.Optional;

public class ResetarSenhaUseCase {

    private final FuncionarioGateway gateway;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final PasswordEncoder passwordEncoder;

    public ResetarSenhaUseCase(FuncionarioGateway gateway, GerenciadorTokenJwt gerenciadorTokenJwt, PasswordEncoder passwordEncoder) {
        this.gateway = gateway;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(String token, String novaSenha) {
        if (!gerenciadorTokenJwt.isTokenValid(token))
            throw new RuntimeException("Token inválido ou expirado");

        String email = gerenciadorTokenJwt.extractEmail(token);

        Optional<Funcionario> user = gateway.findByEmail(email);
        if (user.isEmpty())
            throw new FuncionarioNaoEncontradoException("Usuário não encontrado");

        Funcionario funcionario = user.get();
        funcionario.setSenha(passwordEncoder.encode(novaSenha));
        gateway.save(funcionario);
    }
}
