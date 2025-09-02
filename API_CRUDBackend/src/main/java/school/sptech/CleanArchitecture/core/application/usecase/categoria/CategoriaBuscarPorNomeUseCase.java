package school.sptech.CleanArchitecture.core.application.usecase.categoria;

import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;

public class CategoriaBuscarPorNomeUseCase {

    private final CategoriaGateway gateway;

    public CategoriaBuscarPorNomeUseCase(CategoriaGateway gateway) {
        this.gateway = gateway;
    }

    public Categoria execute(String nome){
        if (gateway.existsByNome(nome)){
            return gateway.findByNome(nome);
        }
        throw new CategoriaNaoEncontradaException("Categoria com o nome "+ nome +" não encontrada.");
    }

}
