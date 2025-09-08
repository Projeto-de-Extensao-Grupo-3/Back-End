package school.sptech.CleanArchitecture.core.application.command.loteItemEstoque;

import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;

public record CriarLoteItemEstoqueCommand (
        Double qtdItem,
        Double preco,
        ItemEstoque itemEstoque,
        Lote lote
){
}
