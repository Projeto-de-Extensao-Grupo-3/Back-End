package school.sptech.CRUDBackend.dto.SaidaEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaidaEstoqueFuncionarioRequestDto {
    @Schema(description = "ID do funcionário que cadastrou o log", example = "1")
    private Integer idFuncionario;
}
