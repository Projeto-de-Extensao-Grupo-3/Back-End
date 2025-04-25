package school.sptech.CRUDBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfeccaoRoupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConfeccaoRoupa;
    @ManyToOne
    private ItemEstoque roupa;
    @ManyToOne
    private ItemEstoque tecido;
}
