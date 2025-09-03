package school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa;

import school.sptech.CleanArchitecture.core.adapters.ConfeccaoRoupaGateway;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.exception.confeccaoRoupa.ConfeccaoRoupaConflitoException;
import school.sptech.CleanArchitecture.core.application.exception.confeccaoRoupa.ConfeccaoRoupaNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public class ConfeccaoRoupaAtualizarUseCase {

    private final ConfeccaoRoupaGateway gateway;

    public ConfeccaoRoupaAtualizarUseCase(ConfeccaoRoupaGateway gateway) {
        this.gateway = gateway;
    }

    public ConfeccaoRoupa execute(ConfeccaoRoupaAtualizarCommand command){
        ItemEstoque tecido = new ItemEstoque();
        tecido.setIdItemEstoque(command.tecido().idTecido());

        ItemEstoque roupa = new ItemEstoque();
        roupa.setIdItemEstoque(command.roupa().idRoupa());

        if (gateway.existsById(command.id())) {
            if (gateway.existsByRoupaAndTecido(roupa, tecido)){
                throw new ConfeccaoRoupaConflitoException("Confecção de Roupa com essa roupa e tecido já cadastrados.");
            }
            ConfeccaoRoupa confeccaoParaAtualizar = new ConfeccaoRoupa();
            confeccaoParaAtualizar.setIdConfeccaoRoupa(command.id());
            confeccaoParaAtualizar.setRoupa(roupa);
            confeccaoParaAtualizar.setTecido(tecido);
            confeccaoParaAtualizar.setQtdTecido(command.qtdTecido());
            return gateway.save(confeccaoParaAtualizar);
        }
        throw new ConfeccaoRoupaNaoEncontradoException("Esse registro de confecção de roupa não existe");
    }
}
