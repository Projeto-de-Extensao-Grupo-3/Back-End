package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import school.sptech.CleanArchitecture.core.domain.entity.*;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.confeccaoRoupa.ConfeccaoRoupaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem.ImagemEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira.PrateleiraEntity;


import java.util.Set;
import java.util.stream.Collectors;

@Schema(description = "Entidade que representa um item de estoque.")
@Entity
@Getter
@Setter
@ToString
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
    private Boolean notificar;
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

    public ItemEstoqueEntity(ItemEstoque itemEstoque) {
        this.idItemEstoque = itemEstoque.getIdItemEstoque();
        this.descricao = itemEstoque.getDescricao();
        this.complemento = itemEstoque.getComplemento();
        this.peso = itemEstoque.getPeso();
        this.qtdMinimo = itemEstoque.getQtdMinimo();
        this.qtdArmazenado = itemEstoque.getQtdArmazenado();
        this.categoria = new CategoriaEntity(itemEstoque.getCategoria().getIdCategoria());
        this.caracteristicas = itemEstoque.getCaracteristicas().stream()
                .map(c -> {
                    CategoriaEntity nova = new CategoriaEntity();
                    nova.setIdCategoria(c.getIdCategoria());
                    return nova;
                })
                .collect(Collectors.toSet());
        this.prateleira = new PrateleiraEntity(itemEstoque.getPrateleira().getIdPrateleira());
        this.confeccaoRoupa = itemEstoque.getConfeccaoRoupa().stream()
                .map(c -> {
                    ConfeccaoRoupaEntity nova = new ConfeccaoRoupaEntity();
                    nova.setIdConfeccaoRoupa(c.getIdConfeccaoRoupa());
                    return nova;
                })
                .collect(Collectors.toSet());
        this.preco = itemEstoque.getPeso();
        this.imagem = new ImagemEntity(itemEstoque.getImagem().getIdImagem());
    }

    public ItemEstoqueEntity(Integer idItemEstoque, String descricao) {
        this.idItemEstoque = idItemEstoque;
        this.descricao = descricao;
    }

    public ItemEstoqueEntity(Integer idItemEstoque) {
        this.idItemEstoque = idItemEstoque;
    }
}
