package school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginacaoResponseDTO<T> {
    private List<T> conteudo;
    private Long totalRegistros;
    private Integer paginaAtual;
    private Integer limite;
}