package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "Entidade representando uma saída do estoque.")
@Entity
@Getter
@Setter
public class SaidaEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSaida;
    private LocalDate data;
    private LocalTime hora;
    private Integer qtdSaida;
    private String motivoSaida;
}
