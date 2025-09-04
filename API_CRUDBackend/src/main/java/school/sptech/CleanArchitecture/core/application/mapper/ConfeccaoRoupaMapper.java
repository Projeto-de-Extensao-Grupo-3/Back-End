package school.sptech.CleanArchitecture.core.application.mapper;


import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaCadastrarCommand;
import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public class ConfeccaoRoupaMapper {


    public static ConfeccaoRoupa ofAtualizarCommand(ConfeccaoRoupaAtualizarCommand command) {
        ConfeccaoRoupa confeccaoRoupa = new ConfeccaoRoupa();

        ItemEstoque tecido = new ItemEstoque();
        tecido.setIdItemEstoque(command.tecido().idTecido());

        ItemEstoque roupa = new ItemEstoque();
        roupa.setIdItemEstoque(command.roupa().idRoupa());

        confeccaoRoupa.setIdConfeccaoRoupa(command.id());
        confeccaoRoupa.setRoupa(roupa);
        confeccaoRoupa.setTecido(tecido);
        confeccaoRoupa.setQtdTecido(command.qtdTecido());
        return confeccaoRoupa;
    }

    public static ConfeccaoRoupa ofCadastrarCommand(ConfeccaoRoupaCadastrarCommand command) {
        ConfeccaoRoupa confeccaoRoupa = new ConfeccaoRoupa();

        ItemEstoque tecido = new ItemEstoque();
        tecido.setIdItemEstoque(command.tecido().idTecido());

        ItemEstoque roupa = new ItemEstoque();
        roupa.setIdItemEstoque(command.roupa().idRoupa());

        confeccaoRoupa.setRoupa(roupa);
        confeccaoRoupa.setTecido(tecido);
        confeccaoRoupa.setQtdTecido(command.qtdTecido());

        return confeccaoRoupa;
    }
}
