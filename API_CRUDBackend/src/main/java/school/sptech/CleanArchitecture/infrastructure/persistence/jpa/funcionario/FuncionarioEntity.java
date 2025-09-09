
package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncionario;

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "controle_acesso",
            joinColumns = @JoinColumn(name = "fk_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "fk_permissao"))
    private Set<PermissaoEntity> permissoes;
}
