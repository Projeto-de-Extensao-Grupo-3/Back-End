package school.sptech.CleanArchitecture.core.application.usecase.confeccaoRoupa;

import school.sptech.CleanArchitecture.core.adapters.ConfeccaoRoupaGateway;
import school.sptech.CleanArchitecture.core.application.command.confeccaoRoupa.ConfeccaoRoupaCadastrarCommand;
import school.sptech.CleanArchitecture.core.application.exceptions.confeccaoRoupa.ConfeccaoRoupaConflitoException;
import school.sptech.CleanArchitecture.core.application.mapper.ConfeccaoRoupaMapper;
import school.sptech.CleanArchitecture.core.domain.entity.ConfeccaoRoupa;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.Set;
import java.util.stream.Collectors;

public class ConfeccaoRoupaCadastrarUseCase {

    private final ConfeccaoRoupaGateway gateway;

    public ConfeccaoRoupaCadastrarUseCase(ConfeccaoRoupaGateway gateway) {
        this.gateway = gateway;
    }

    public Set<ConfeccaoRoupa> execute(Integer id, Set<ConfeccaoRoupaCadastrarCommand> command){
        gateway.deleteAllByRoupa_IdItemEstoqueEquals(id);
        Set<ConfeccaoRoupa> confeccaoRoupas = command.stream().map(confeccao -> {
                    ItemEstoque tecido = new ItemEstoque();
                    tecido.setIdItemEstoque(confeccao.tecido().idTecido());
                    ItemEstoque roupa = new ItemEstoque();
                    roupa.setIdItemEstoque(confeccao.roupa().idRoupa());

                    return new ConfeccaoRoupa(roupa, tecido, confeccao.qtdTecido());
                }
        ).collect(Collectors.toSet());

        return gateway.saveAll(confeccaoRoupas);
    }
}
