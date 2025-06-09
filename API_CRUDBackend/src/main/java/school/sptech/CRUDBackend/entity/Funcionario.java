package school.sptech.CRUDBackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Schema(description = "Entidade que representa um funcionário.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funcionario {

    public Funcionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncionario;
    @Schema(description = "Nome do funcionário", example = "Fernando", required = true)
    private String nome;
    @Schema(description = "CPF do funcionário", example = "2025-04-12T10:15:30", required = true)
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    @ManyToMany
    @JoinTable(
            name = "controle_acesso",
            joinColumns = @JoinColumn(name = "fk_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "fk_permissao"))
    @Schema(description = "Lista de permissões.")
    private Set<Permissao> permissoes;
}
