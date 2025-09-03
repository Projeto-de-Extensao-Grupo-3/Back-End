package school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa;

public record ConfeccaoRoupaCadastrarCommand(
        ConfeccaoRoupaRoupaCommand roupa,
        ConfeccaoRoupaTecidoCommand tecido,
        Double qtdTecido) {
}
