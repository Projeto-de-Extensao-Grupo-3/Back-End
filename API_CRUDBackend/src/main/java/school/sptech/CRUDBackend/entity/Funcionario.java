package school.sptech.CRUDBackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Schema(description = "Entidade representando um funcionário.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
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
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_permissao"))
    private Set<Permissao> permissoes;
}
