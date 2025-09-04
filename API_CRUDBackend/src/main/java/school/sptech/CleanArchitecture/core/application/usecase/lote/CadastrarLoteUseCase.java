package school.sptech.CleanArchitecture.core.application.usecase.lote;

import school.sptech.CRUDBackend.exception.Lote.LoteConflitoException;
import school.sptech.CleanArchitecture.core.adapters.LoteGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Lote;

public class CadastrarLoteUseCase {

    private final LoteGateway gateway;

    public CadastrarLoteUseCase(LoteGateway gateway) {
        this.gateway = gateway;
    }

    public Lote executar(Lote lote){
        if (gateway.existsByDescricao(lote.getDescricao())) {
            throw new LoteConflitoException("Lote já cadastrado");
        } else {
            return gateway.save(lote);
        }
    }
}
