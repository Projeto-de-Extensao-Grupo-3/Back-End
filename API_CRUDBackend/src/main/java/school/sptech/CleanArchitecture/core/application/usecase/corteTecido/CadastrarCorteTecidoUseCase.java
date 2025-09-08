package school.sptech.CleanArchitecture.core.application.usecase.corteTecido;

import school.sptech.CleanArchitecture.core.adapters.CorteTecidoGateway;
import school.sptech.CleanArchitecture.core.application.command.corteTecido.CriarCorteTecidoCommand;
import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;

public class CadastrarCorteTecidoUseCase {

    private final CorteTecidoGateway gateway;

    public CadastrarCorteTecidoUseCase(CorteTecidoGateway gateway) {
        this.gateway = gateway;
    }

    public CorteTecido executar(CriarCorteTecidoCommand command){
        var corteTecidoParaRegistrar = new CorteTecido();

        corteTecidoParaRegistrar.setInicio(command.inicio());
        corteTecidoParaRegistrar.setTermino(command.temino());
        corteTecidoParaRegistrar.setFuncionario(command.funcionario().getIdFuncionario());
        corteTecidoParaRegistrar.setLoteItemEstoque(command.loteItemEstoque().getIdLoteItemEstoque());

        return gateway.save(corteTecidoParaRegistrar);
    }
}
