package school.sptech.CRUDBackend.dto.imagem;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DTO para cadastro de uma Imagem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagemRequestDto {
    @Schema(description = "URL da imagem, que se encontra em um Bucket S3", example = "exemplo_https://aws.s3/roupa1.jpg")
    @NotBlank
    private String url;
}
