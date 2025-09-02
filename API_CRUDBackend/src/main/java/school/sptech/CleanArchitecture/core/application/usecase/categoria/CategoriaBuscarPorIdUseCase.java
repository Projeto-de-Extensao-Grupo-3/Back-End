package school.sptech.CleanArchitecture.core.application.usecase.categoria;

import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;

public class CategoriaBuscarPorIdUseCase {

    private final CategoriaGateway gateway;

    public CategoriaBuscarPorIdUseCase(CategoriaGateway gateway) {
        this.gateway = gateway;
    }

    public Categoria executar(Integer id){
       if (gateway.existsById(id)){
           return gateway.findById(id);
       }
       throw new CategoriaNaoEncontradaException("Categoria com ID "+ id +" não encontrada.");
    }

}
