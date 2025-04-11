package school.sptech.CRUDBackend.dto.SaidaEstoque;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class SaidaEstoqueResponseDto {

    private Integer idSaida;
    private LocalDate data;
    private LocalTime hora;
    private Integer qtSaida;
    private String motivoSaida;

}
