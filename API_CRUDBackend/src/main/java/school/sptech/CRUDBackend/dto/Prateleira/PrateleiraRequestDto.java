package school.sptech.CRUDBackend.dto.Prateleira;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrateleiraRequestDto {

    @NotBlank
    @NotNull
    private String codigo;
}
