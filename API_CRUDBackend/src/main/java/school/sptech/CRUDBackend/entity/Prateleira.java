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

@Schema(description = "Entidade que representa uma prateleira (Local onde o item está armazenado).")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prateleira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrateleira;
    @Schema(example = "1A")
    private String codigo;
}
