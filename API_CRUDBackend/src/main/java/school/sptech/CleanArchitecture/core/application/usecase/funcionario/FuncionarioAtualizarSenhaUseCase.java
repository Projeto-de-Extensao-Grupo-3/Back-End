package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.application.command.funcionario.AlterarSenhaCommand;
import school.sptech.CleanArchitecture.core.application.exceptions.funcionario.SenhaException;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

public class FuncionarioAtualizarSenhaUseCase {

    private final FuncionarioGateway gateway;

    private final BuscarFuncionarioPorIdUseCase buscarFuncionarioPorIdUseCase;

    private final PasswordEncoder passwordEncoder;

    public FuncionarioAtualizarSenhaUseCase(FuncionarioGateway gateway, BuscarFuncionarioPorIdUseCase buscarFuncionarioPorIdUseCase, PasswordEncoder passwordEncoder) {
        this.gateway = gateway;
        this.buscarFuncionarioPorIdUseCase = buscarFuncionarioPorIdUseCase;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(Integer id, AlterarSenhaCommand command){
        Funcionario funcionario = buscarFuncionarioPorIdUseCase.execute(id);

        if (!passwordEncoder.matches(command.senhaAtual(), funcionario.getSenha())) {
            throw new SenhaException("Senha atual incorreta");
        }
        System.out.println("Senha antiga: " + funcionario.getSenha());
        funcionario.setSenha(passwordEncoder.encode(command.novaSenha()));
        System.out.println("Senha Nova: "+ funcionario.getSenha());
        gateway.save(funcionario);
    }

}
