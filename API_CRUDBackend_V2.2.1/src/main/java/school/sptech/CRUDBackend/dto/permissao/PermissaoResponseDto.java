package school.sptech.CRUDBackend.dto.permissao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoResponseDto {
    private Integer id;
    private String descricao;
}
