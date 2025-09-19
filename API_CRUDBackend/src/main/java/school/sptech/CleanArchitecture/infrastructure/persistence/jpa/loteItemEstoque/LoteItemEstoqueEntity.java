package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.lote.LoteEntity;

@Schema(description = "Entidade que identifica quais itens do estoque chegaram em um lote específico.")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "lote_item_estoque")
public class LoteItemEstoqueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLoteItemEstoque;
    private Double qtdItem;
    private Double preco;
    @ManyToOne
    @JoinColumn(name = "fk_item_estoque")
    private ItemEstoqueEntity itemEstoque;
    @ManyToOne
    @JoinColumn(name = "fk_lote")
    @Schema(description = "Representa o lote do item.")
    private LoteEntity lote;

    public LoteItemEstoqueEntity(LoteItemEstoque loteItemEstoque) {
        this.idLoteItemEstoque = loteItemEstoque.getIdLoteItemEstoque();
    }
}


