package school.sptech.CleanArchitecture.core.application.usecase.corteTecido;

import school.sptech.CRUDBackend.exception.Lote.LoteNaoEncontradoException;
import school.sptech.CRUDBackend.exception.corteTecido.CorteTecidoNaoEncontradoException;
import school.sptech.CleanArchitecture.core.adapters.CorteTecidoGateway;
import school.sptech.CleanArchitecture.core.application.command.corteTecido.AtualizarCorteTecidoCommand;
import school.sptech.CleanArchitecture.core.domain.entity.CorteTecido;

public class AtualizarCorteTecidoPorIdUseCase {

    private final CorteTecidoGateway gateway;

    public AtualizarCorteTecidoPorIdUseCase(CorteTecidoGateway gateway) {
        this.gateway = gateway;
    }

    public CorteTecido executar(AtualizarCorteTecidoCommand command) {

        if (gateway.existsById(command.idCorteTecido())) {
            var corteTecidoParaAtualizar = new CorteTecido();
            corteTecidoParaAtualizar.setIdCorteTecido(command.idCorteTecido());
            corteTecidoParaAtualizar.setInicio(command.inicio());
            corteTecidoParaAtualizar.setTermino(command.temino());
            corteTecidoParaAtualizar.setFuncionario(command.funcionario());
            corteTecidoParaAtualizar.setLoteItemEstoque(command.loteItemEstoque());
            return gateway.save(corteTecidoParaAtualizar);
        }

        throw new CorteTecidoNaoEncontradoException("Corte tecido não encontrado");
    }
}
