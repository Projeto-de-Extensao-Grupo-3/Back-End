package school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEstoqueCategoriaResponseDto {
    private String nome;
    private ItemEstoqueCategoriaPaiResponseDto categoriaPai;
}
