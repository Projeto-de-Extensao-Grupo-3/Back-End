package school.sptech.CRUDBackend.dto.corteTecido;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorteTecidoResponseDto {
    private Integer id;
    private LocalDateTime inicio;
    private LocalDateTime termino;
}
