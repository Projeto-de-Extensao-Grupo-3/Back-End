package school.sptech.CRUDBackend.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class SaidaEstoque {

    private Integer idSaida;
    private LocalDate data;
    private LocalTime hora;
    private Integer qtdSaida;
    private String motivoSaida;
}
