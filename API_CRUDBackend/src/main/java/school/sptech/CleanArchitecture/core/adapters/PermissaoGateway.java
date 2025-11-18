package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.Permissao;

import java.util.List;

public interface PermissaoGateway {
    List<Permissao> findAll();
}
