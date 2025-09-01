package school.sptech.CleanArchitecture.core.application.usecase.categoria;

import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;

import java.util.List;

public class CategoriaListAllUseCase {

    private final CategoriaGateway gateway;

    public CategoriaListAllUseCase(CategoriaGateway gateway) {
        this.gateway = gateway;
    }

    public List<Categoria> executar(){
        return gateway.findAll();
    }

}
