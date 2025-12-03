package school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemEstoqueImagemResponseDto {
    private Integer id;
    private String url;

    public ItemEstoqueImagemResponseDto(Integer id, String url, String bucket) {
        this.id = id;
        this.url = "https://" + bucket + ".s3.us-east-1.amazonaws.com/" + url;
    }
}
