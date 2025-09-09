package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.exception.SaidaEstoque.SaidaEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

import java.time.LocalDate;
import java.util.List;

public class SaidaEstoqueBuscarPorDataUseCase {

    private final SaidaEstoqueGateway gateway;

    public SaidaEstoqueBuscarPorDataUseCase(SaidaEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<SaidaEstoque> execute(LocalDate data){
        List<SaidaEstoque> saidaPorData = gateway.findByData(data);

        if (saidaPorData.isEmpty()){
            throw new SaidaEstoqueNaoEncontradoException("Nenhuma saída para esta data foi encontrada");
        }
        return saidaPorData;
    }
}
