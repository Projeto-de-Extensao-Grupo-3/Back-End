package school.sptech.CRUDBackend.dto.permissao;

import school.sptech.CRUDBackend.entity.Permissao;

import java.util.List;

public class PermissaoMapper {
    public static Permissao toEntity(PermissaoRequestDto requestDto) {
        return new Permissao(
                null,
                requestDto.getDescricao()
        );
    }

    public static PermissaoResponseDto toResponseDto(Permissao permissao) {
        return new PermissaoResponseDto(
                permissao.getId(),
                permissao.getDescricao()
        );
    }

    public static List<PermissaoResponseDto> toResponseDtos(List<Permissao> permissoes) {
        return permissoes
                .stream()
                .map(PermissaoMapper::toResponseDto)
                .toList();
    }
}
