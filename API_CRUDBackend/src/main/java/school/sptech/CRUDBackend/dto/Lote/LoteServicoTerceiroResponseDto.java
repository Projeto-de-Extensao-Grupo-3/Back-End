package school.sptech.CRUDBackend.dto.Lote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteServicoTerceiroResponseDto {
    private String categoria;
    private String nome;
    private String telefone;
    private String email;
}
