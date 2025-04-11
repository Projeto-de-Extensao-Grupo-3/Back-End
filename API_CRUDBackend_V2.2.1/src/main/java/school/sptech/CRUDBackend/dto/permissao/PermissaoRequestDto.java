package school.sptech.CRUDBackend.dto.permissao;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoRequestDto {
    @NotBlank
    private String descricao;
}
