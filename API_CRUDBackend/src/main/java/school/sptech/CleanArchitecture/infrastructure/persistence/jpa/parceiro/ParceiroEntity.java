package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;

@Schema(description = "Entidade que representa um parceiro.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parceiro")
public class ParceiroEntity {
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