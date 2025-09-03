package school.sptech.CleanArchitecture.core.application.usecase.parceiro;

import school.sptech.CleanArchitecture.core.adapters.ParceiroGateway;
import school.sptech.CleanArchitecture.core.application.command.parceiro.CriarParceiroCommand;
import school.sptech.CleanArchitecture.core.application.exception.parceiro.ParceiroConflitoException;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

public class CadastrarParceiroUseCase {

    private final ParceiroGateway gateway;

    public CadastrarParceiroUseCase(ParceiroGateway gateway) {
        this.gateway = gateway;
    }

    public Parceiro executar(CriarParceiroCommand command) {

        if (gateway.existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(
                command.email(),
                command.identificacao(),
                command.endereco()
        )) {
            throw new ParceiroConflitoException("Esse provedor de serviço já foi cadastrado.");
        }

        var parceiroParaRegistrar = new Parceiro(
                command.categoria(),
                command.nome(),
                command.telefone(),
                command.email(),
                command.endereco(),
                command.identificacao()
        );

        return gateway.save(parceiroParaRegistrar);
    }
}
