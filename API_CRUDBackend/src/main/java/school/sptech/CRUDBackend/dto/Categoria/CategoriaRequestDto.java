package school.sptech.CRUDBackend.dto.Categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Dto para requisição de Categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDto {
    @Schema(description = "Entrada de uma categoria", example = "Listrada")
    @NotBlank
    private String nome;
    private CategoriaPaiRequestDto categoriaPai;
}
