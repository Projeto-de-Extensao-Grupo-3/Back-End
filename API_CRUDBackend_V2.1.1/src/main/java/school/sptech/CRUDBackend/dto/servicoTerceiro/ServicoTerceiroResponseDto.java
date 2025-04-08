package school.sptech.CRUDBackend.dto.servicoTerceiro;

import lombok.Setter;

@Setter
public class ServicoTerceiroResponseDto {
    private Integer idServicoTerceiro;
    private String categoria;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String identificacao;
}
