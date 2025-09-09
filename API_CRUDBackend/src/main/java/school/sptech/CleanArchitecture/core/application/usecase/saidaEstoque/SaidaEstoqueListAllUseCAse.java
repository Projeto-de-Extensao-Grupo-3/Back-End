package school.sptech.CleanArchitecture.core.application.usecase.saidaEstoque;

import school.sptech.CleanArchitecture.core.adapters.SaidaEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.SaidaEstoque;

import java.util.List;

public class SaidaEstoqueListAllUseCAse {

    private final SaidaEstoqueGateway gateway;

    public SaidaEstoqueListAllUseCAse(SaidaEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<SaidaEstoque> execute(){
        return gateway.findAll();
    }
}
