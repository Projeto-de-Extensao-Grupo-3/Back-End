package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Permissao;

import java.util.List;

public class FuncionarioListarPorPermissaoUserCase {

    private final FuncionarioGateway gateway;

    public FuncionarioListarPorPermissaoUserCase(FuncionarioGateway gateway) {
        this.gateway = gateway;
    }

    public List<Funcionario> execute(Permissao permissao){
        return gateway.findByPermissoes_IdPermissao(permissao.getIdPermissao());
    }

}
