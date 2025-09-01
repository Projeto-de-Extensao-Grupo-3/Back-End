package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa.ConfeccaoRoupaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira.PrateleiraEntity;


import java.util.Set;

@Schema(description = "Entidade que representa um item de estoque.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_estoque")
public class ItemEstoqueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItemEstoque;
    private String descricao;
    private String complemento;
    private Double peso;
    private Double qtdMinimo;
    private Double qtdArmazenado;
    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private CategoriaEntity categoria;
    @ManyToMany
    @JoinTable(
            name = "caracteristica_item_estoque",
            joinColumns = @JoinColumn(name = "fk_item_estoque"),
            inverseJoinColumns = @JoinColumn(name = "fk_categoria")
    )
    private Set<CategoriaEntity> caracteristicas;
    @ManyToOne
    @JoinColumn(name = "fk_prateleira")
    @Schema(description = "Local onde o item está localizado (em qual prateleira).")
    private PrateleiraEntity prateleira;
    @OneToMany(mappedBy = "roupa")
    private Set<ConfeccaoRoupaEntity> confeccaoRoupa;
    private Double preco;
    @OneToOne
    @JoinColumn(name = "fk_imagem")
    private ImagemEntity imagem;
}
