package school.sptech.CRUDBackend.dto.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioPermissaoRequestDto {
    @Schema(description = "id da permissão", example = "1")
    private Integer idPermissao;
    @Schema(description = "descrição da permissão", example = "Visualizar dashboard")
    private String descricao;
}
