package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imagem")
public class ImagemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagem;
    private String url;

    public ImagemEntity(Integer idImagem) {
        this.idImagem = idImagem;
    }
}