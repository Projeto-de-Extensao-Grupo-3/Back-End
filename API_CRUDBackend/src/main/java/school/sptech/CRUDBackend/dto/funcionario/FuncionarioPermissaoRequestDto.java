package school.sptech.CRUDBackend.dto.funcionario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioPermissaoRequestDto {
    private Integer idPermissao;
    private String descricao;
}
