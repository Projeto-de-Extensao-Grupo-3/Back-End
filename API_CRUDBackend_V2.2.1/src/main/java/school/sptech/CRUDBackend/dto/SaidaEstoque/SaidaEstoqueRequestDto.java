package school.sptech.CRUDBackend.dto.SaidaEstoque;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class SaidaEstoqueRequestDto {

    @PastOrPresent
    @NotNull
    private LocalDate data;
    @NotNull(message = "O campo hora é obrigatório")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hora;
    @Min(1)
    private Integer qtSaida;
    private String motivoSaida;
}
