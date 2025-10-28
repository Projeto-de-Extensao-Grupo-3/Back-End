package school.sptech.CleanArchitecture.infrastructure.web.rabbitmq;

import lombok.Data;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class EmailDto {
    private SaidaEstoqueEmailDto saidaEstoque;
    private ItemEstoqueEmailDto itemEstoque;
    private LoteEmailDto lote;
    private FuncionarioEmailDto funcionario;
    private List<FuncionarioEmailDto> funcionariosParaNotificar;

    public EmailDto(SaidaEstoque saida, ItemEstoque item, List<Funcionario> funcionariosParaNotificar) {
        this.saidaEstoque = new SaidaEstoqueEmailDto(saida);
        this.itemEstoque = new ItemEstoqueEmailDto(item);
        this.lote = new LoteEmailDto(saida.getLoteItemEstoque());
        this.funcionario = new FuncionarioEmailDto(saida.getResponsavel());
        this.funcionariosParaNotificar = funcionariosParaNotificar.stream().map(f -> new FuncionarioEmailDto(f)).collect(Collectors.toList());
    }
}
