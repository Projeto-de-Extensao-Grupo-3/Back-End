package school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa;

import school.sptech.CRUDBackend.exception.confeccaoRoupa.ConfeccaoRoupaConflitoException;
import school.sptech.CleanArchitecture.core.adapters.ConfeccaoRoupaGateway;

public class ConfeccaoRoupaDeletarUseCase {

    private final ConfeccaoRoupaGateway gateway;

    public ConfeccaoRoupaDeletarUseCase(ConfeccaoRoupaGateway gateway) {
        this.gateway = gateway;
    }

    public Void execute(Integer id){
        if (gateway.existsById(id)) {
            gateway.deleteById(id);
        }
        throw new ConfeccaoRoupaConflitoException("Confecção de roupa com id "+ id+ "não encontrado.");
    }
}
