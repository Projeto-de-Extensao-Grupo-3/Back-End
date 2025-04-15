package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Entidade representando um serviço terceiro.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoTerceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicoTerceiro;
    private String categoria;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String identificacao;
}
