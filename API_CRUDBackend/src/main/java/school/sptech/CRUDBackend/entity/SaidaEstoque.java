package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "Entidade que representa a saída de um item.", example = "Venda, ou saída para costura")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaidaEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSaida;
    private LocalDate data;
    private LocalTime hora;
    private Double qtdSaida;
    private String motivoSaida;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario responsavel;
    @ManyToOne
    @JoinColumn(name = "id_lote_item_estoque")
    private LoteItemEstoque loteItemEstoque;
    @ManyToOne
    @JoinColumn(name = "id_servico_terceiro")
    @Nullable
    private ServicoTerceiro costureira;
}
