package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.alerta;

import school.sptech.CleanArchitecture.core.domain.entity.Alerta;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.alerta.AlertaCriacaoDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.alerta.AlertaItemEstoqueDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.alerta.AlertaResponseDto;

import java.util.List;
import java.util.Objects;

public class AlertaEntityMapper {

    public static AlertaEntity ofDomain (Alerta domain){
        if (Objects.isNull(domain)) {
            return null;
        }

        var enitity = new AlertaEntity();
        ItemEstoqueEntity itemEstoqueEntity = new ItemEstoqueEntity();
        itemEstoqueEntity.setIdItemEstoque(domain.getItemEstoque().getIdItemEstoque());
        itemEstoqueEntity.setComplemento(domain.getItemEstoque().getComplemento());
        itemEstoqueEntity.setDescricao(domain.getItemEstoque().getDescricao());
        itemEstoqueEntity.setQtdMinimo(domain.getItemEstoque().getQtdMinimo());
        itemEstoqueEntity.setQtdArmazenado(domain.getItemEstoque().getQtdArmazenado());

        System.out.println("ItemEstoqueEntity para ser salvo no banco: " + itemEstoqueEntity);

        enitity.setIdAlerta(domain.getIdAlerta());
        enitity.setDescricao(domain.getDescricao());
        enitity.setDataHora(domain.getDataHora());
        enitity.setItemEstoque(itemEstoqueEntity);

        return enitity;
    }

    public static Alerta ofEntity(AlertaEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        var domain = new Alerta();
        ItemEstoque itemEstoque = new ItemEstoque();
        itemEstoque.setIdItemEstoque(entity.getItemEstoque().getIdItemEstoque());
        domain.setIdAlerta(entity.getIdAlerta());
        domain.setDescricao(entity.getDescricao());
        domain.setDataHora(entity.getDataHora());
        domain.setItemEstoque(itemEstoque);

        return domain;
    }

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
                .map(AlertaEntityMapper::toResponseDto)
                .toList();
    }

}
