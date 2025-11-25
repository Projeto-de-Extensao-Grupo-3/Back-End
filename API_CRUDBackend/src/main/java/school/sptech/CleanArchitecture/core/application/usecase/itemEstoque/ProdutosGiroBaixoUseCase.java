package school.sptech.CleanArchitecture.core.application.usecase.itemEstoque;

import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.ProdutoBaixoGiroDto;

import java.util.List;

public class ProdutosGiroBaixoUseCase {

    private final ItemEstoqueGateway gateway;

    public ProdutosGiroBaixoUseCase(ItemEstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public List<ProdutoBaixoGiroDto> execute(){
        return gateway.produtoGiroBaixo();
    }
}