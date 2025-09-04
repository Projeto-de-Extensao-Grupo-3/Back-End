package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa;

import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaRoupaCommand;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaTecidoCommand;
import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.confeccaoRoupa.ConfeccaoRoupaRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.confeccaoRoupa.ConfeccaoRoupaResponseDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.confeccaoRoupa.ConfeccaoRoupaRoupaRequestDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.confeccaoRoupa.ConfeccaoRoupaTecidoRequestDto;

import java.util.Objects;

public class ConfeccaoRoupaEntityMapper {

    public static ConfeccaoRoupaEntity ofDomain (ConfeccaoRoupa domain){
        if (Objects.isNull(domain)) {
            return null;
        }
        var entity = new ConfeccaoRoupaEntity();
        
        ItemEstoqueEntity roupaEntity = new ItemEstoqueEntity();
        roupaEntity.setIdItemEstoque(domain.getRoupa().getIdItemEstoque());
        
        ItemEstoqueEntity tecidoEntity = new ItemEstoqueEntity();
        tecidoEntity.setIdItemEstoque(domain.getTecido().getIdItemEstoque());

        entity.setIdConfeccaoRoupa(domain.getIdConfeccaoRoupa());
        entity.setRoupa(roupaEntity);
        entity.setTecido(tecidoEntity);
        entity.setQtdTecido(domain.getQtdTecido());

        return entity;
    }

    public static ConfeccaoRoupa ofEntity(ConfeccaoRoupaEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        var domain = new ConfeccaoRoupa();
        
        ItemEstoque roupaDomain = new ItemEstoque();
        roupaDomain.setIdItemEstoque(entity.getRoupa().getIdItemEstoque());

        ItemEstoque tecidoDomain = new ItemEstoque();
        tecidoDomain.setIdItemEstoque(entity.getTecido().getIdItemEstoque());

        domain.setIdConfeccaoRoupa(entity.getIdConfeccaoRoupa());
        domain.setRoupa(roupaDomain);
        domain.setTecido(tecidoDomain);
        domain.setQtdTecido(entity.getQtdTecido());

        return domain;
    }

    public static ConfeccaoRoupa toEntity(ConfeccaoRoupaRequestDto requestDto) {

        ConfeccaoRoupaRoupaRequestDto roupaRequestDto = requestDto.getRoupa();
        ItemEstoque roupa = new ItemEstoque();
        roupa.setIdItemEstoque(roupaRequestDto.getIdRoupa());

        ConfeccaoRoupaTecidoRequestDto tecidoRequestDto = requestDto.getTecido();
        ItemEstoque tecido = new ItemEstoque();
        tecido.setIdItemEstoque(tecidoRequestDto.getIdTecido());

        return new ConfeccaoRoupa(
                null,
                roupa,
                tecido,
                requestDto.getQtdTecido()
        );
    }

    public static ConfeccaoRoupaResponseDto toResponseDto(ConfeccaoRoupa confeccaoRoupa) {
        return new ConfeccaoRoupaResponseDto(
                confeccaoRoupa.getIdConfeccaoRoupa()
        );
    }

    public static ConfeccaoRoupaCadastrarCommand toCadastrarCommand(ConfeccaoRoupaRequestDto dto) {
        ConfeccaoRoupaRoupaCommand roupa = new ConfeccaoRoupaRoupaCommand(dto.getRoupa().getIdRoupa());

        ConfeccaoRoupaTecidoCommand tecido = new ConfeccaoRoupaTecidoCommand(dto.getTecido().getIdTecido());

        return new ConfeccaoRoupaCadastrarCommand(
                roupa,
                tecido,
                dto.getQtdTecido()
        );
    }

    public static ConfeccaoRoupaAtualizarCommand toAtualizarCommand(Integer id, ConfeccaoRoupaRequestDto dto){
        ConfeccaoRoupaRoupaCommand roupa = new ConfeccaoRoupaRoupaCommand(dto.getRoupa().getIdRoupa());

        ConfeccaoRoupaTecidoCommand tecido = new ConfeccaoRoupaTecidoCommand(dto.getTecido().getIdTecido());

        return new ConfeccaoRoupaAtualizarCommand(
                id,
                roupa,
                tecido,
                dto.getQtdTecido()
        );
    }

}
