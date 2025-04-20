package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
