package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Entidade que representa a categoria (tecido ou roupa), subcategorias (Tipos de roupas e tipo de tecido) e características gerais.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;
    private String nome;
    @Nullable
    @ManyToOne
    @JoinColumn(name = "fk_categoria_pai")
    @Schema(description = "Categoria principal de um item", example = "Roupa ou tecido")
    private CategoriaEntity categoriaPai;

    public CategoriaEntity(Integer idCategoria, String nome) {
        this.idCategoria = idCategoria;
        this.nome = nome;
    }

    public CategoriaEntity(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}
