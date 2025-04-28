package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Schema(description = "Entidade representando um item de estoque.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItemEstoque;
    private String descricao;
    private String complemento;
    private Double peso;
    private Double qtdMinimo;
    private Double qtdArmazenado;
    @ManyToOne
    private Categoria categoria;
    @ManyToMany
    @JoinTable(
            name = "caracteristica_item_estoque",
            joinColumns = @JoinColumn(name = "id_item_estoque"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> caracteristicas;
    @ManyToOne
    private Prateleira prateleira;
}
