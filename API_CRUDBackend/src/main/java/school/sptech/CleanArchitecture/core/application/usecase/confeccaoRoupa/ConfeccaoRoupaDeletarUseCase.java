package school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa;

import school.sptech.CRUDBackend.exception.confeccaoRoupa.ConfeccaoRoupaConflitoException;
import school.sptech.CleanArchitecture.core.adapters.ConfeccaoRoupaGateway;

public class ConfeccaoRoupaDeletarUseCase {

    private final ConfeccaoRoupaGateway gateway;

    public ConfeccaoRoupaDeletarUseCase(ConfeccaoRoupaGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Integer id){
        gateway.deleteAllByRoupa_IdItemEstoqueEquals(id);
    }
}
