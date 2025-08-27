package school.sptech.CleanArchitecture.core.application.command.alerta;
import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public class AlertaMapper {
    public static Alerta toEntity(AlertaCriacaoCommand alertaDto) {
        ItemEstoque itemEstoque = new ItemEstoque();
        itemEstoque.setIdItemEstoque(alertaDto.itemEstoque().idItemEstoque());
        return new Alerta(
                null,
                alertaDto.descricao(),
                alertaDto.dataHora(),
                itemEstoque
        );
    }

    public static AlertaResponseCommand toResponseDto(Alerta alerta) {
        ItemEstoque itemEstoque = alerta.getItemEstoque();
        AlertaItemEstoqueCommand itemEstoqueDto = new AlertaItemEstoqueCommand(
                itemEstoque.getIdItemEstoque(),
                itemEstoque.getDescricao(),
                itemEstoque.getComplemento(),
                itemEstoque.getQtdMinimo(),
                itemEstoque.getQtdArmazenado()
        );
        return new AlertaResponseCommand(
                alerta.getIdAlerta(),
                alerta.getDescricao(),
                alerta.getDataHora(),
                itemEstoqueDto
        );
    }

    public static List<AlertaResponseCommand> toResponseDtos(List<Alerta> alertas) {
        return alertas
                .stream()
                .map(AlertaMapper::toResponseDto)
                .toList();
    }
}
