package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.corteTecido;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque.LoteItemEstoqueEntity;

@Schema(description = "Entidade que representa o corte de um tecido.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorteTecidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCorteTecido;
    @Schema(description = "Data e Hora do inicio do corte", example = "2025-04-12T10:15:30")
    private String inicio;
    @Schema(description = "Data e Hora da finalização do corte", example = "2025-04-12T10:15:30")
    private String termino;
    @ManyToOne
    @JoinColumn(name = "fk_funcionario")
    @Schema(description = "Funcionário responsável pelo corte")
    private FuncionarioEntity funcionario;
    @ManyToOne
    @JoinColumn(name = "fk_lote_item_estoque")
    @Schema(description = "Lote ao qual o tecido que foi cortado pertence.")
    private LoteItemEstoqueEntity loteItemEstoque;
}