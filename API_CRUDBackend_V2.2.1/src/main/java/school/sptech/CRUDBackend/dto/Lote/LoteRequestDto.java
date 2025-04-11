package school.sptech.CRUDBackend.dto.Lote;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoteRequestDto {

    @NotBlank
    private String descricao;
    @NotBlank
    @PastOrPresent
    private LocalDateTime dataEntrada;

}
