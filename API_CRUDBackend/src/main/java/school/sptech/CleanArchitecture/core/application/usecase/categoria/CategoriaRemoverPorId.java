package school.sptech.CleanArchitecture.core.application.usecase.categoria;

import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;

public class CategoriaRemoverPorId {

    private final CategoriaGateway gateway;

    public CategoriaRemoverPorId(CategoriaGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Integer id){
        if (gateway.existsById(id)) {
            gateway.deleteById(id);
        } else {
            throw new CategoriaNaoEncontradaException("Categoria com ID " + id + " não encontrada");
        }
    }
}
