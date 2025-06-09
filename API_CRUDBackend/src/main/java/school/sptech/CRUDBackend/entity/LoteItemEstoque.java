package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Schema(description = "Entidade que identifica quais itens do estoque chegaram em um lote específico.")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoteItemEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLoteItemEstoque;
    private Double qtdItem;
    private Double preco;
    @ManyToOne
    @JoinColumn(name = "fk_item_estoque")
    private ItemEstoque itemEstoque;
    @ManyToOne
    @JoinColumn(name = "fk_lote")
    @Schema(description = "Representa o lote do item.")
    private Lote lote;
}
