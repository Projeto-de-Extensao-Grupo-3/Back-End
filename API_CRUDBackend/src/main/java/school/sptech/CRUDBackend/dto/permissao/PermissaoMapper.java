package school.sptech.CRUDBackend.dto.permissao;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.CRUDBackend.entity.Permissao;

import java.util.List;

@Schema(description = "Classe de mapeamento de DTOs Permissao.")
public class PermissaoMapper {

    public static PermissaoResponseDto toResponseDto(Permissao permissao) {
        return new PermissaoResponseDto(
                permissao.getIdPermissao(),
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
