package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

import java.time.LocalDateTime;

@Schema(description = "Entidade representando um lote de item.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLote;
    private String descricao;
    private LocalDateTime dataEntrada;
    @ManyToOne
    @JoinColumn(name = "fk_parceiro")
    @Schema(description = "Atributo que representa de qual parceiro veio.", example = "Costureira ou fornecedor.")
    private Parceiro parceiro;
    @ManyToOne
    @JoinColumn(name = "fk_responsavel")
    @Schema(description = "Funcionário responsável pelo registro.")
    private Funcionario responsavel;
}
