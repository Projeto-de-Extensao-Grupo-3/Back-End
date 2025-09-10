package school.sptech.CleanArchitecture.infrastructure.web.dto.parceiro;

import school.sptech.CleanArchitecture.core.application.command.parceiro.AtualizarParceiroCommand;
import school.sptech.CleanArchitecture.core.application.command.parceiro.CriarParceiroCommand;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro.ParceiroEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ParceiroMapper {

    public static CriarParceiroCommand toCriarCommand(ParceiroRequestDto dto) {
        return new CriarParceiroCommand(
                dto.getCategoria(),
                dto.getNome(),
                dto.getTelefone(),
                new EmailVo(dto.getEmail()),
                dto.getEndereco(),
                dto.getIdentificacao()
        );
    }

    public static AtualizarParceiroCommand toAtualizarCommand(Integer id, ParceiroRequestDto dto) {
        return new AtualizarParceiroCommand(
                id,
                dto.getCategoria(),
                dto.getNome(),
                dto.getTelefone(),
                new EmailVo(dto.getEmail()),
                dto.getEndereco(),
                dto.getIdentificacao()
        );
    }

    public static ParceiroResponseDto toResponseDto(ParceiroEntity entity) {
        return new ParceiroResponseDto(
                entity.getIdParceiro(),
                entity.getCategoria(),
                entity.getNome(),
                entity.getTelefone(),
                entity.getEmail(),
                entity.getEndereco(),
                entity.getIdentificacao()
        );
    }

    public static List<ParceiroResponseDto> toResponseDtos(List<ParceiroEntity> entities) {
        return entities
                .stream()
                .map(ParceiroMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
