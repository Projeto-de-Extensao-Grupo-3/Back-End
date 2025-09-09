
package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.adapters.PasswordEncoderGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

public class CadastrarFuncionarioUseCase {

    private final FuncionarioGateway gateway;
    private final PasswordEncoderGateway passwordEncoder;

    public CadastrarFuncionarioUseCase(FuncionarioGateway gateway, PasswordEncoderGateway passwordEncoder) {
        this.gateway = gateway;
        this.passwordEncoder = passwordEncoder;
    }

    public Funcionario executar(CriarFuncionarioCommand command) {
        if (gateway.existsByCpfOrEmail(command.cpf(), command.email())) {
            throw new RuntimeException("Este funcionário já existe no sistema.");
        }
        Funcionario funcionario = new Funcionario(
                null,
                command.nome(),
                command.cpf(),
                command.telefone(),
                command.email(),
                passwordEncoder.encode(command.senha()),
                command.permissoes()
        );
        return gateway.save(funcionario);
    }
}
