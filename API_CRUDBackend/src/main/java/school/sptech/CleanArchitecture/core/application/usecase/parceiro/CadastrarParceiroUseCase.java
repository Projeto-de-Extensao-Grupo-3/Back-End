package school.sptech.CleanArchitecture.core.application.usecase.parceiro;

import school.sptech.CleanArchitecture.core.adapters.ParceiroGateway;
import school.sptech.CleanArchitecture.core.application.command.parceiro.CriarParceiroCommand;
import school.sptech.CleanArchitecture.core.application.exceptions.parceiro.ParceiroConflitoException;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;


public class CadastrarParceiroUseCase {

    private final ParceiroGateway gateway;

    public CadastrarParceiroUseCase(ParceiroGateway gateway) {
        this.gateway = gateway;
    }

    public Parceiro executar(CriarParceiroCommand command) {

        if (gateway.existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(
                command.email().getValue(),
                command.identificacao(),
                command.endereco()
        )) {
            throw new ParceiroConflitoException("Esse provedor de serviço já foi cadastrado.");
        }

        var parceiroParaRegistrar = new Parceiro(
                command.categoria(),
                command.nome(),
                command.telefone().getValue(),
                command.email(),
                command.endereco(),
                command.identificacao()
        );

        return gateway.save(parceiroParaRegistrar);
    }
}
