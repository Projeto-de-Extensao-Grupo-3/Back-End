package school.sptech.CleanArchitecture.core.application.usecase.permissao;

import school.sptech.CleanArchitecture.core.adapters.PermissaoGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Permissao;

import java.util.List;

public class ListarTodasPermissoesUseCase {
    private final PermissaoGateway gateway;

    public ListarTodasPermissoesUseCase(PermissaoGateway gateway) {
        this.gateway = gateway;
    }

    public List<Permissao> execute() {
        return gateway.findAll();
    }
}
