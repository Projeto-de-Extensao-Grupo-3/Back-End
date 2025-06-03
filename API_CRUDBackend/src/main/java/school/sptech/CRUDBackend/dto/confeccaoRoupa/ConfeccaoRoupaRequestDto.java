package school.sptech.CRUDBackend.dto.confeccaoRoupa;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConfeccaoRoupaRequestDto {
    @NotNull
    private ConfeccaoRoupaRoupaRequestDto roupa;
    @NotNull
    private ConfeccaoRoupaTecidoRequestDto tecido;
    @NotNull
    private Double qtdTecido;
}
