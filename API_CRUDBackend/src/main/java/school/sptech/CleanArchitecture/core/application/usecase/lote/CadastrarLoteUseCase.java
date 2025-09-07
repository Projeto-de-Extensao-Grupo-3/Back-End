package school.sptech.CleanArchitecture.core.application.usecase.lote;

import school.sptech.CRUDBackend.exception.Lote.LoteConflitoException;
import school.sptech.CleanArchitecture.core.adapters.LoteGateway;
import school.sptech.CleanArchitecture.core.application.command.lote.CriarLoteCommand;
import school.sptech.CleanArchitecture.core.application.mapper.LoteMapper;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;

public class CadastrarLoteUseCase {

    private final LoteGateway gateway;

    public CadastrarLoteUseCase(LoteGateway gateway) {
        this.gateway = gateway;
    }

    public Lote executar(CriarLoteCommand command){
        if (gateway.existsByDescricao(command.descricao())) {
            throw new LoteConflitoException("Lote já cadastrado");
        }

        Lote lote = LoteMapper.ofCriarLoteCommand(command);
        return gateway.save(lote);
    }
}
