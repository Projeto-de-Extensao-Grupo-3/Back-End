package school.sptech.CRUDBackend.dto.permissao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para respsota de uma permissão.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoResponseDto {
    private Integer id;
    @Schema(description = "Descreve a função da permissão.", example = "Consegue alterar permissões de funcionários.")
    private String descricao;
}
