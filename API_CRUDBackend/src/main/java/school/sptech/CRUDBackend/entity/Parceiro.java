package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Schema(description = "Entidade que representa um parceiro.")
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParceiro;
    @Schema(example = "Costureira ou fornecedor.")
    private String categoria;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String identificacao;
}
