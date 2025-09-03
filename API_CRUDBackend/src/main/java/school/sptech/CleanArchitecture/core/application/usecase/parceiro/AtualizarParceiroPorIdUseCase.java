package school.sptech.CleanArchitecture.core.application.usecase.parceiro;

import school.sptech.CleanArchitecture.core.adapters.ParceiroGateway;
import school.sptech.CleanArchitecture.core.application.command.parceiro.AtualizarParceiroCommand;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

public class AtualizarParceiroPorIdUseCase {

    private final ParceiroGateway gateway;

    public AtualizarParceiroPorIdUseCase(ParceiroGateway gateway) {
        this.gateway = gateway;
    }

    public Parceiro executar(AtualizarParceiroCommand command) {
        if(gateway.existsById(command.id())){
            var parceiroParaAtualizar = new Parceiro();
            parceiroParaAtualizar.setId(command.id());
            parceiroParaAtualizar.setNome(command.nome());
            parceiroParaAtualizar.setEmail(command.email());
            parceiroParaAtualizar.setIdentificacao(command.identificacao());
            parceiroParaAtualizar.setEndereco(command.endereco());
            parceiroParaAtualizar.setCategoria(command.categoria());
            return gateway.save(parceiroParaAtualizar);
        }
        throw new RuntimeException("Não foi possivel encontrar o parceiro com id: " + command.id());
    }
}
