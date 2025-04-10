package school.sptech.CRUDBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idLote;
    private String descricao;
    private LocalDateTime dataEntrada;
}
