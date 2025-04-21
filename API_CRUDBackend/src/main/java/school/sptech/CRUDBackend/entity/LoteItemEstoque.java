package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Entidade representando um lote de item do estoque.")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoteItemEstoque {

    public LoteItemEstoque(Integer idLoteItemEstoque) {
        this.idLoteItemEstoque = idLoteItemEstoque;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLoteItemEstoque;
    private Integer qtdItem;
    private Double preco;
    @ManyToOne
    @JoinColumn(name = "id_item_estoque")
    private ItemEstoque itemEstoque;
    @ManyToOne
    @JoinColumn(name = "id_lote")
    private Lote lote;
}
