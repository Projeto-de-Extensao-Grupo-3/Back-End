package school.sptech.CleanArchitecture.core.application.usecase.funcionario;

import school.sptech.CleanArchitecture.core.adapters.FuncionarioGateway;
import school.sptech.CleanArchitecture.core.application.command.funcionario.FuncionarioAtualizarPorIdCommand;
import school.sptech.CleanArchitecture.core.application.exceptions.funcionario.FuncionarioNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;
import school.sptech.CleanArchitecture.infrastructure.web.dto.funcionario.FuncionarioMapper;

public class FuncionarioAtualizarPorIdUseCase {

    private final FuncionarioGateway gateway;

    private final BuscarFuncionarioPorIdUseCase buscarFuncionarioPorIdUseCase;

    public FuncionarioAtualizarPorIdUseCase(FuncionarioGateway gateway, BuscarFuncionarioPorIdUseCase buscarFuncionarioPorIdUseCase) {
        this.gateway = gateway;
        this.buscarFuncionarioPorIdUseCase = buscarFuncionarioPorIdUseCase;
    }

    public Funcionario execute(FuncionarioAtualizarPorIdCommand command){
        if (gateway.existsById(command.idFuncionario())) {
            Funcionario funcionarioParaAtualizar = FuncionarioMapper.ofAtualizarCommand(command);
            Funcionario funcionarioCompleto = buscarFuncionarioPorIdUseCase.execute(funcionarioParaAtualizar.getIdFuncionario());
            funcionarioParaAtualizar.setSenha(funcionarioCompleto.getSenha());
            return gateway.save(funcionarioParaAtualizar);
        }
        throw new FuncionarioNaoEncontradoException("O funcionário para atualizar não existe.");
    }
}
