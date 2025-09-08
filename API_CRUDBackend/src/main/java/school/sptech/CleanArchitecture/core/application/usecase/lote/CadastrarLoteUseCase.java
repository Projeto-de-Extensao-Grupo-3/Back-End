package school.sptech.CleanArchitecture.core.application.usecase.lote;

import school.sptech.CRUDBackend.exception.Lote.LoteConflitoException;
import school.sptech.CleanArchitecture.core.adapters.LoteGateway;
import school.sptech.CleanArchitecture.core.application.command.lote.CriarLoteCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

public class CadastrarLoteUseCase {

    private final LoteGateway gateway;

    public CadastrarLoteUseCase(LoteGateway gateway) {
        this.gateway = gateway;
    }

    public Lote executar(CriarLoteCommand command){
        if (gateway.existsByDescricao(command.descricao())) {
            throw new LoteConflitoException("Lote já cadastrado");
        }

        var loteParaRegistrar = new Lote();

        Parceiro parceiro = new Parceiro();
        parceiro.setId(command.parceiro());

        Funcionario responsavel = new Funcionario();
        responsavel.setIdFuncionario(command.responsavel());

        loteParaRegistrar.setDescricao(command.descricao());
        loteParaRegistrar.setDataEntrada(command.dataEntrada());
        loteParaRegistrar.setParceiro(parceiro);
        loteParaRegistrar.setResponsavel(responsavel);

        return gateway.save(loteParaRegistrar);
    }
}
