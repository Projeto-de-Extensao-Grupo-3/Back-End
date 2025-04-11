package school.sptech.CRUDBackend.dto.corteTecido;

import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorteTecidoRequestDto {
    @PastOrPresent
    private LocalDateTime inicio;
    @PastOrPresent
    private LocalDateTime termino;
}
