package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro.ParceiroEntity;

import java.time.LocalDateTime;

@Schema(description = "Entidade representando um lote de item.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lote")
public class LoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLote;
    private String descricao;
    @Column(name = "dt_entrada")
    private LocalDateTime dataEntrada;
    @ManyToOne
    @JoinColumn(name = "fk_parceiro")
    @Schema(description = "Atributo que representa de qual parceiro veio.", example = "Costureira ou fornecedor.")
    private ParceiroEntity parceiro;
    @ManyToOne
    @JoinColumn(name = "fk_responsavel")
    @Schema(description = "Funcionário responsável pelo registro.")
    private FuncionarioEntity responsavel;
}
