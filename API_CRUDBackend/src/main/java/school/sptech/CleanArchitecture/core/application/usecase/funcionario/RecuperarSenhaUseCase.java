package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import school.sptech.CRUDBackend.config.GerenciadorTokenJwt;
import school.sptech.CleanArchitecture.core.adapters.EmailService;
import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;

public class RecuperarSenhaUseCase {

    private final FuncionarioGateway gateway;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final EmailService emailService;

    public RecuperarSenhaUseCase(FuncionarioGateway gateway, GerenciadorTokenJwt gerenciadorTokenJwt, EmailService emailService) {
        this.gateway = gateway;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.emailService = emailService;
    }

    public void execute(String email) {
        var user = gateway.findByEmail(email);
        if (user.isEmpty()) return; // não revela se email existe

        String token = gerenciadorTokenJwt.generateTokenForPasswordReset(email);

        String link = "http://localhost:5173/criar-nova-senha?token=" + token;

        emailService.send(
                email,
                "Venuste - Recuperação de Senha",
                "Clique para resetar sua senha: " + link
        );
        System.out.println("EMAIL PARA RECUPERAÇÃO DE SENHA ENVIADO COM SUCESSO!!");
    }
}
