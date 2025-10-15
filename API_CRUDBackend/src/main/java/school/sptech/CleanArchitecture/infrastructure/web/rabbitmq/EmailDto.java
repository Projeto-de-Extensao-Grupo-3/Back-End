package school.sptech.CleanArchitecture.infrastructure.web.rabbitmq;

import lombok.Data;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

@Data
public class EmailDto {
    private SaidaEstoqueEmailDto saidaEstoque;
    private ItemEstoqueEmailDto itemEstoque;
    private LoteEmailDto lote;
    private FuncionarioEmailDto funcionario;

    public EmailDto(SaidaEstoque saida, ItemEstoque item) {
        this.saidaEstoque = new SaidaEstoqueEmailDto(saida);
        this.itemEstoque = new ItemEstoqueEmailDto(item);
        this.lote = new LoteEmailDto(saida.getLoteItemEstoque());
        this.funcionario = new FuncionarioEmailDto(saida.getResponsavel());
    }
}
