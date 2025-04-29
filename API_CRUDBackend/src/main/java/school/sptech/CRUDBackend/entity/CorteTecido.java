package school.sptech.CRUDBackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "Entidade que representa o corte de um tecido.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorteTecido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCorteTecido;
    @Schema(description = "Data e Hora do inicio do corte", example = "2025-04-12T10:15:30")
    private String inicio;
    @Schema(description = "Data e Hora da finalização do corte", example = "2025-04-12T10:15:30")
    private String termino;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    @Schema(description = "Funcionário responsável pelo corte")
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name = "id_lote_item_estoque")
    @Schema(description = "Lote ao qual o tecido que foi cortado pertence.")
    private LoteItemEstoque loteItemEstoque;
}
