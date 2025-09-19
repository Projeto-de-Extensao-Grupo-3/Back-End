
package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario;

import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Permissao;
import school.sptech.CleanArchitecture.core.domain.valueObject.CpfVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.EmailVo;
import school.sptech.CleanArchitecture.core.domain.valueObject.TelefoneVo;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.permissao.PermissaoEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class FuncionarioEntityMapper {

    public static Funcionario ofEntity(FuncionarioEntity e) {
        if (e == null) return null;
        Funcionario d = new Funcionario();
        d.setIdFuncionario(e.getIdFuncionario());
        d.setNome(e.getNome());
        d.setCpf(new CpfVo(e.getCpf()));
        d.setTelefone(new TelefoneVo(e.getTelefone()));
        d.setEmail(new EmailVo(e.getEmail()));
        d.setSenha(e.getSenha());

        if (e.getPermissoes() != null) {
            Set<Permissao> permissoes = e.getPermissoes().stream()
                    .map(p -> new Permissao(p.getIdPermissao(), p.getDescricao()))
                    .collect(Collectors.toSet());
            d.setPermissoes(permissoes);
        }
        return d;
    }

    public static FuncionarioEntity ofDomain(Funcionario d) {
        if (d == null) return null;
        FuncionarioEntity e = new FuncionarioEntity();
        e.setIdFuncionario(d.getIdFuncionario());
        e.setNome(d.getNome());

        e.setCpf(d.getCpf().getValue());


        e.setTelefone(d.getTelefone().getValue());
        e.setEmail(d.getEmail().getValue());
        e.setSenha(d.getSenha());


        if (d.getPermissoes() != null) {
            e.setPermissoes(d.getPermissoes().stream()
                    .map(p -> PermissaoEntity.builder()
                            .idPermissao(p.getIdPermissao())
                            .descricao(p.getDescricao())
                            .build())
                    .collect(Collectors.toSet()));
        }


        return e;
    }
}
