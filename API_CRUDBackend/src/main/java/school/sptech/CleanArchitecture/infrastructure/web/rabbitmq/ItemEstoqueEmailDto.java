package school.sptech.CleanArchitecture.infrastructure.web.rabbitmq;

import lombok.Data;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

@Data
public class ItemEstoqueEmailDto {
    private String descricao;
    private Double preco;
    private Double qtdArmazenado;


    public ItemEstoqueEmailDto(ItemEstoque item) {
        this.descricao = item.getDescricao();
        this.preco = item.getPreco();
        this.qtdArmazenado = item.getQtdArmazenado();
    }
}
