package school.sptech.CleanArchitecture.core.application.command.funcionario;

public record AlterarSenhaCommand(
        String senhaAtual,
        String novaSenha
) {
}
