package school.sptech.CleanArchitecture.core.application.dto.alerta;

import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;

public class AlertaMapper {
    public static Alerta toEntity(AlertaCriacaoDto alertaDto) {
        ItemEstoque itemEstoque = new ItemEstoque();
        itemEstoque.setIdItemEstoque(alertaDto.getItemEstoque().getIdItemEstoque());
        return new Alerta(
                null,
                alertaDto.getDescricao(),
                alertaDto.getDataHora(),
                itemEstoque
        );
    }

    public static AlertaResponseDto toResponseDto(Alerta alerta) {
        ItemEstoque itemEstoque = alerta.getItemEstoque();
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

    public static List<AlertaResponseDto> toResponseDtos(List<Alerta> alertas) {
        return alertas
                .stream()
                .map(AlertaMapper::toResponseDto)
                .toList();
    }
}
