package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Entidade que representa a confecção de uma roupa.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfeccaoRoupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConfeccaoRoupa;
    @ManyToOne
    @Schema(description = "A roupa que foi utilizada na confecção.", example = "Vestido")
    private ItemEstoque roupa;
    @ManyToOne
    @Schema(description = "O tecido que foi utilizado na confecção.", example = "Couro")
    private ItemEstoque tecido;
}
