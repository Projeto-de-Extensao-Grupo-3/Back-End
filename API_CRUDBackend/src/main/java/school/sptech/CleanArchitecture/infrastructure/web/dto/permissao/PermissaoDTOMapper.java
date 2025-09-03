package school.sptech.CleanArchitecture.infrastructure.web.dto.permissao;

import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.permissao.PermissaoEntity;

public class PermissaoDTOMapper {

    public static PermissaoListDTO toResponseDto(PermissaoEntity permissao) {
        return new PermissaoListDTO(permissao.getIdPermissao(), permissao.getDescricao());
    }
}
