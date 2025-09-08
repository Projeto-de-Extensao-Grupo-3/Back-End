package school.sptech.CleanArchitecture.core.application.usecase.lote;

import school.sptech.CRUDBackend.exception.Lote.LoteNaoEncontradoException;
import school.sptech.CleanArchitecture.core.adapters.LoteGateway;
import school.sptech.CleanArchitecture.core.application.command.lote.AtualizarLotePorIdCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

public class AtualizarLotePorIdUseCase {

    private final LoteGateway gateway;

    public AtualizarLotePorIdUseCase(LoteGateway gateway) {
        this.gateway = gateway;
    }

    public Lote executar(AtualizarLotePorIdCommand command) {

        if (gateway.existsById(command.idLote())){
            var loteParaAtualizar = new Lote();

            Parceiro parceiro = new Parceiro();
            parceiro.setId(command.parceiro());

            Funcionario responsavel = new Funcionario();
            responsavel.setIdFuncionario(command.responsavel());

            loteParaAtualizar.setIdLote(command.idLote());
            loteParaAtualizar.setDescricao(command.descricao());
            loteParaAtualizar.setDataEntrada(command.dataEntrada());
            loteParaAtualizar.setParceiro(parceiro);
            loteParaAtualizar.setResponsavel(responsavel);
            return gateway.save(loteParaAtualizar);
        }
        throw new LoteNaoEncontradoException("Lote não encontrado");
    }
}
