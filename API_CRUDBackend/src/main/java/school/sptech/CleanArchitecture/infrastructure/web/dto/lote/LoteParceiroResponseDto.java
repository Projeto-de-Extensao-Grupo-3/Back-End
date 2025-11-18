package school.sptech.CleanArchitecture.infrastructure.web.dto.lote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteParceiroResponseDto {
    private String categoria;
    private String nome;
    private String telefone;
    private String email;
}
