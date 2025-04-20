package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Entidade representando um lote de item.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLote;
    private String descricao;
    private String dataEntrada;
    @ManyToOne
    @JoinColumn(name = "id_servico_terceiro")
    private ServicoTerceiro servicoTerceiro;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario responsavel;
}
