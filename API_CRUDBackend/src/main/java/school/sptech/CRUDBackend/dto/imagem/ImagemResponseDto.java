package school.sptech.CRUDBackend.dto.imagem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagemResponseDto {
    private Integer idImagem;
    private String url;
}
