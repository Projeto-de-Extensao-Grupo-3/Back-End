package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jdk.jfr.Description;
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
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;
    private String nome;
    @Nullable
    @ManyToOne
    @JoinColumn(name = "fk_categoria_pai")
    @Schema(description = "Categoria principal de um item", example = "Roupa ou tecido")
    private Categoria categoriaPai;
}
