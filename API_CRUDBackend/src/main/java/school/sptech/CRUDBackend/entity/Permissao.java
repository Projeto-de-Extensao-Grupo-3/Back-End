package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Schema(description = "Entidade que representa uma permissão.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermissao;
    @Schema(example = "Acesso a dashboard.")
    private String descricao;
}
