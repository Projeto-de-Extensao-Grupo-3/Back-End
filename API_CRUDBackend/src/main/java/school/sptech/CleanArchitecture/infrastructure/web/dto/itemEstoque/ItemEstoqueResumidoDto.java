package school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class    ItemEstoqueResumidoDto {
    private Integer idItem;
    private String descricao;
    private String tipoItem;
    private Double preco;
    private String urlImagem;

    public ItemEstoqueResumidoDto(Integer idItem, String descricao, String tipoItem, Double preco, String urlImagem, String bucket) {
        this.idItem = idItem;
        this.descricao = descricao;
        this.tipoItem = tipoItem;
        this.preco = preco;
        this.urlImagem = "https://" + bucket + ".s3.us-east-1.amazonaws.com/" + urlImagem;
    }
}
