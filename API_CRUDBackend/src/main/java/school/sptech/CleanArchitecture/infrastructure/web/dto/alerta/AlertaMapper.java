package school.sptech.CleanArchitecture.infrastructure.web.dto.alerta;

import school.sptech.CleanArchitecture.core.application.command.alerta.CriarAlertaCommand;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.alerta.AlertaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;

import java.util.List;

public class AlertaMapper {
    public static AlertaEntity toEntity(AlertaCriacaoDto alertaDto) {
        ItemEstoqueEntity itemEstoque = new ItemEstoqueEntity();
        itemEstoque.setIdItemEstoque(alertaDto.getItemEstoque().getIdItemEstoque());
        return new AlertaEntity(
                null,
                alertaDto.getDescricao(),
                alertaDto.getDataHora(),
                itemEstoque
        );
    }

    public static AlertaResponseDto toResponseDto(AlertaEntity alerta) {
        ItemEstoqueEntity itemEstoque = alerta.getItemEstoque();
        AlertaItemEstoqueDto itemEstoqueDto = new AlertaItemEstoqueDto(
                itemEstoque.getIdItemEstoque(),
                itemEstoque.getDescricao(),
                itemEstoque.getComplemento(),
                itemEstoque.getQtdMinimo(),
                itemEstoque.getQtdArmazenado()
        );
        return new AlertaResponseDto(
                alerta.getIdAlerta(),
                alerta.getDescricao(),
                alerta.getDataHora(),
                itemEstoqueDto
        );
    }

    public static List<AlertaResponseDto> toResponseDtos(List<AlertaEntity> alertas) {
        return alertas
                .stream()
                .map(AlertaMapper::toResponseDto)
                .toList();
    }

    public static CriarAlertaCommand toCommand(AlertaCriacaoDto dto){
        CriarAlertaCommand command = new CriarAlertaCommand();
        AlertaItemEstoqueDto itemEstoqueDto = dto.getItemEstoque();
        ItemEstoque itemEstoque = new ItemEstoque(itemEstoqueDto);
        command.setDescricao(dto.getDescricao());
        command.setDataHora(dto.getDataHora());
        command.setItemEstoque(itemEstoque);
        return command;
    }
}
