
package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.permissao.PermissaoEntity;

import java.util.Set;

@Schema(description = "Entidade que representa um funcionário.")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "funcionario")
public class FuncionarioEntity {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "controle_acesso",
            joinColumns = @JoinColumn(name = "fk_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "fk_permissao"))
    @Schema(description = "Lista de permissões.")
    private Set<PermissaoEntity> permissoes;

    public FuncionarioEntity(Funcionario funcionario) {
        this.idFuncionario = funcionario.getIdFuncionario();
        this.nome = funcionario.getNome();
        this.telefone = funcionario.getTelefone().getValue();
        this.email = funcionario.getEmail().getValue();
    }
}
