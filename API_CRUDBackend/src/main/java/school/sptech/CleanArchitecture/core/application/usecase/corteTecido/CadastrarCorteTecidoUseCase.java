package school.sptech.CleanArchitecture.core.application.usecase.corteTecido;

import school.sptech.CleanArchitecture.core.adapters.CorteTecidoGateway;
import school.sptech.CleanArchitecture.core.application.command.corteTecido.CriarCorteTecidoCommand;
import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;

public class CadastrarCorteTecidoUseCase {

    private final CorteTecidoGateway gateway;

    public CadastrarCorteTecidoUseCase(CorteTecidoGateway gateway) {
        this.gateway = gateway;
    }

    public CorteTecido executar(CriarCorteTecidoCommand command){
        CorteTecido corteTecidoParaRegistrar = new CorteTecido();

        corteTecidoParaRegistrar.setInicio(command.inicio());
        corteTecidoParaRegistrar.setTermino(command.temino());
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(command.funcionario());
        corteTecidoParaRegistrar.setFuncionario(funcionario);
        LoteItemEstoque loteItemEstoque = new LoteItemEstoque();
        loteItemEstoque.setIdLoteItemEstoque(command.loteItemEstoque());
        corteTecidoParaRegistrar.setLoteItemEstoque(loteItemEstoque);

        return gateway.save(corteTecidoParaRegistrar);
    }
}
