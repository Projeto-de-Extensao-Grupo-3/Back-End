package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.funcionario.SenhaRegraDeNegocioException;
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

    public void execute(Integer id, String senha){
        if (senha.length() < 8){
            throw new SenhaRegraDeNegocioException("A senha deve ter ao menos 8 digitos.");
        }
        boolean temCaracterEspecial = senha.matches(".*[^a-zA-Z0-9 ].*");
        if (temCaracterEspecial){
            throw new SenhaRegraDeNegocioException("A senha deve ter ao menos 1 caracter especial.");
        }
        Funcionario funcionario = buscarFuncionarioPorIdUseCase.execute(id);
        funcionario.setSenha(passwordEncoder.encode(senha));
        gateway.save(funcionario);
    }

}
