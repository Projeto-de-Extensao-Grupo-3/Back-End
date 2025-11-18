package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Schema(description = "Entidade que representa uma prateleira (Local onde o item está armazenado).")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prateleira")
@ToString
public class PrateleiraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrateleira;
    @Schema(example = "1A")
    private String codigo;

    public PrateleiraEntity(Integer idPrateleira) {
        this.idPrateleira = idPrateleira;
    }

}
