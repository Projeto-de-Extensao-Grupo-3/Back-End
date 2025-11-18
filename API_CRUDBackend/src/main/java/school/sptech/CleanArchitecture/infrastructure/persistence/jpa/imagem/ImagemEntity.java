package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imagem")
@ToString
public class ImagemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagem;
    private String url;

    public ImagemEntity(Integer idImagem) {
        this.idImagem = idImagem;
    }
}