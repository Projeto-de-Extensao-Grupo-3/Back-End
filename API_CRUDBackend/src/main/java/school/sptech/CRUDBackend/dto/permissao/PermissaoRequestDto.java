package school.sptech.CRUDBackend.dto.permissao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para requisição de uma nova permissão.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoRequestDto {
    @Schema(description = "Descreve a função da permissão.", example = "Consegue alterar permissões de funcionários.")
    @NotBlank
    private String descricao;
}
