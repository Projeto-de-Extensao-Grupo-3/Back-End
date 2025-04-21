package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Schema(description = "Entidade representando um lote de item.")
@Getter
@Setter
@Entity
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLote;
    private String descricao;
    private LocalDateTime dataEntrada;
    @ManyToOne
    @JoinColumn(name = "id_servico_terceiro")
    private ServicoTerceiro servicoTerceiro;
}
