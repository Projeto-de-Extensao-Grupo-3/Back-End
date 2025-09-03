package school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa;

public record ConfeccaoRoupaAtualizarCommand(
        Integer id,
        ConfeccaoRoupaRoupaCommand roupa,
        ConfeccaoRoupaTecidoCommand tecido,
        Double qtdTecido
) {
}
