package school.sptech.CRUDBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ServicoTerceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicoTerceiro;
    private String categoria;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String identificacao;
}
