package school.sptech.CleanArchitecture.core.application.mapper;

import school.sptech.CleanArchitecture.core.application.command.alerta.CriarAlertaCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.web.dto.alerta.AlertaCriacaoDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.alerta.AlertaItemEstoqueDto;

public class AlertaMapper {


    public static CriarAlertaCommand toCommand(AlertaCriacaoDto dto){
        CriarAlertaCommand command = new CriarAlertaCommand();
        AlertaItemEstoqueDto itemEstoqueDto = dto.getItemEstoque();
        ItemEstoque itemEstoque = new ItemEstoque(itemEstoqueDto);
        command.setDescricao(dto.getDescricao());
        command.setDataHora(dto.getDataHora());
        command.setItemEstoque(itemEstoque);
        return command;
    }

    public static Alerta ofCriarCommand(CriarAlertaCommand command) {
        Alerta alerta = new Alerta();
        alerta.setDescricao(command.getDescricao());
        alerta.setItemEstoque(command.getItemEstoque());
        alerta.setDataHora(command.getDataHora());
        return alerta;
    }
}
