package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.exception.SaidaEstoque.SaidaEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

import java.util.Optional;

public class SaidaEstoqueBuscarPorIdUseCase {

    private final SaidaEstoqueGateway gateway;

    public SaidaEstoqueBuscarPorIdUseCase(SaidaEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public SaidaEstoque execute(Integer id){
        Optional<SaidaEstoque> saidaPossivel = gateway.findById(id);

        return saidaPossivel.orElseThrow(()
                -> new SaidaEstoqueNaoEncontradoException("A saída procurada não existe"));
    }
}
