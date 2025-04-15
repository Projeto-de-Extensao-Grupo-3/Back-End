package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Entidade representando um lote de item do estoque.")
@Entity
@Setter
@Getter
public class LoteItemEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private Integer qtdItem;
    private Double preco;
}
