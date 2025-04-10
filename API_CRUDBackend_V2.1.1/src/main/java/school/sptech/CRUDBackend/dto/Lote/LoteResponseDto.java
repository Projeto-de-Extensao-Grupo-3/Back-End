package school.sptech.CRUDBackend.dto.Lote;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoteResponseDto {

    private Integer idLote;
    private String descricao;
    private LocalDateTime dataEntrada;
}
