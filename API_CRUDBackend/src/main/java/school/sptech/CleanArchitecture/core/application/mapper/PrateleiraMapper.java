package school.sptech.CleanArchitecture.core.application.mapper;

import school.sptech.CleanArchitecture.core.application.command.prateleira.CriarPrateleiraCommand;
import school.sptech.CleanArchitecture.core.application.command.prateleira.PrateleiraAtualizarCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;

public class PrateleiraMapper {


    public static Prateleira ofCriarPrateleiraCommand(CriarPrateleiraCommand command) {
        Prateleira prateleira = new Prateleira();
        prateleira.setCodigo(command.codigo());
        return prateleira;
    }

    public static Prateleira ofAtualizarPrateleiraCommand(PrateleiraAtualizarCommand command) {
        Prateleira prateleira = new Prateleira();
        prateleira.setIdPrateleira(command.id());
        prateleira.setCodigo(command.codigo());
        return prateleira;
    }
}
