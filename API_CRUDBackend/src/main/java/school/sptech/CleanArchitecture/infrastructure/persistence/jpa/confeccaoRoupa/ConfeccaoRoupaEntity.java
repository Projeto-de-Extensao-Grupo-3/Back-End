package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;

@Schema(description = "Entidade que representa a confecção de uma roupa.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "confeccao_roupa")
public class ConfeccaoRoupaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConfeccaoRoupa;
    @ManyToOne
    @JoinColumn(name = "fk_roupa")
    @Schema(description = "A roupa que foi utilizada na confecção.", example = "Vestido")
    private ItemEstoqueEntity roupa;
    @ManyToOne
    @JoinColumn(name = "fk_tecido")
    @Schema(description = "O tecido que foi utilizado na confecção.", example = "Couro")
    private ItemEstoqueEntity tecido;
    @Schema(description = "A quantidade de tecida utilizada em uma roupa", example = "10.0")
    private Double qtdTecido;
}
