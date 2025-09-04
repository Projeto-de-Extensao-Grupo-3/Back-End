package school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa;

import school.sptech.CleanArchitecture.core.adapters.ConfeccaoRoupaGateway;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.exception.confeccaoRoupa.ConfeccaoRoupaConflitoException;
import school.sptech.CleanArchitecture.core.application.mapper.ConfeccaoRoupaMapper;
import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public class ConfeccaoRoupaCadastrarUseCase {

    private final ConfeccaoRoupaGateway gateway;

    public ConfeccaoRoupaCadastrarUseCase(ConfeccaoRoupaGateway gateway) {
        this.gateway = gateway;
    }

    public ConfeccaoRoupa execute(ConfeccaoRoupaCadastrarCommand command){
        ItemEstoque tecido = new ItemEstoque();
        tecido.setIdItemEstoque(command.tecido().idTecido());

        ItemEstoque roupa = new ItemEstoque();
        roupa.setIdItemEstoque(command.roupa().idRoupa());

        if (gateway.existsByRoupaAndTecido(roupa, tecido)){
            throw new ConfeccaoRoupaConflitoException("Confecção de Roupa com essa roupa e tecido já cadastrados.");
        }
        ConfeccaoRoupa confeccaoPAraCadastrar = ConfeccaoRoupaMapper.ofCadastrarCommand(command);

        return gateway.save(confeccaoPAraCadastrar);
    }
}
