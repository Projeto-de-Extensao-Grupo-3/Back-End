package school.sptech.CleanArchitecture.infrastructure.web.dto.saidaEstoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaidaEstoqueCadastroDto {
    private Integer idSaida;
    private LocalDate data;
    private LocalTime hora;
    private Double qtdSaida;
    private String motivoSaida;
}
