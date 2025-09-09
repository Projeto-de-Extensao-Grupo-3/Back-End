package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.exception.SaidaEstoque.SaidaEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

import java.util.List;

public class SaidaEstoqueBuscarPorMotivoUseCase {

    private final SaidaEstoqueGateway gateway;

    public SaidaEstoqueBuscarPorMotivoUseCase(SaidaEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<SaidaEstoque> execute(String motivo){
        List<SaidaEstoque> saidaPorMotivo = gateway.findByMotivoSaida(motivo);

        if (saidaPorMotivo.isEmpty()){
            throw new SaidaEstoqueNaoEncontradoException("Nenhuma saída com este motivo foi encontrada");
        }
        return saidaPorMotivo;
    }
}
