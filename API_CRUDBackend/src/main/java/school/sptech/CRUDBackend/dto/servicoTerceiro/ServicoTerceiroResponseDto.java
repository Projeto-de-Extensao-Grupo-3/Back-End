package school.sptech.CRUDBackend.dto.servicoTerceiro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoTerceiroResponseDto {
    private Integer idServicoTerceiro;
    private String categoria;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String identificacao;
}
