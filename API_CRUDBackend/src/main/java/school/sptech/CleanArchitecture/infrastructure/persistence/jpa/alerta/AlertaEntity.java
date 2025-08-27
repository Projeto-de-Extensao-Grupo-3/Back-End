package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.alerta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlerta;
    private String descricao;
    private LocalDateTime dataHora;
    @ManyToOne
    @JoinColumn(name = "fk_item_estoque")
    private ItemEstoque itemEstoque;
}
