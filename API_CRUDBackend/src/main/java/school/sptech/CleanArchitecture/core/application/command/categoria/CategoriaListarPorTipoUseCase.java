package school.sptech.CleanArchitecture.core.application.command.categoria;

import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;

import java.util.List;

public class CategoriaListarPorTipoUseCase {

    private final CategoriaGateway gateway;

    public CategoriaListarPorTipoUseCase(CategoriaGateway gateway) {
        this.gateway = gateway;
    }

    public List<Categoria> execute(String tipo){
        return gateway.findByTipo(tipo);
    }

}
