package school.sptech.CleanArchitecture.infrastructure.web.rabbitmq;

import lombok.Data;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;

@Data
public class LoteEmailDto {
    private Integer idLote;
    private Double precoLote;

    public LoteEmailDto(LoteItemEstoque loteItemEstoque) {
        this.idLote = loteItemEstoque.getIdLoteItemEstoque();
        this.precoLote = loteItemEstoque.getPreco();
    }
}
