package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "Entidade representando um corte de tecido.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorteTecido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCorteTecido;
    @Schema(description = "Data e Hora e inicio do corte", example = "2025-04-12T10:15:30")
    private LocalDateTime inicio;
    @Schema(description = "Data e Hora e finalização do corte", example = "2025-04-12T10:15:30")
    private LocalDateTime termino;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name = "id")
    private LoteItemEstoque loteItemEstoque;
}
