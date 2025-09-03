package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.permissao;

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
@Table(name = "permissao")
public class PermissaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermissao;
    @Schema(example = "Acesso a dashboard.")
    private String descricao;
}
