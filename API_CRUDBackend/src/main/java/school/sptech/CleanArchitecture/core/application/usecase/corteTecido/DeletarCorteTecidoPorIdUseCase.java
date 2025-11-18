package school.sptech.CleanArchitecture.core.application.usecase.corteTecido;

import school.sptech.CRUDBackend.exception.corteTecido.CorteTecidoNaoEncontradoException;
import school.sptech.CleanArchitecture.core.adapters.CorteTecidoGateway;

public class DeletarCorteTecidoPorIdUseCase {

    private final CorteTecidoGateway gateway;

    public DeletarCorteTecidoPorIdUseCase(CorteTecidoGateway gateway) {
        this.gateway = gateway;
    }

    public void executar(Integer id){
        if(gateway.existsById(id)){
            gateway.deleteById(id);
        }
        throw new CorteTecidoNaoEncontradoException("Corte de tecido não encontrado");
    }
}
