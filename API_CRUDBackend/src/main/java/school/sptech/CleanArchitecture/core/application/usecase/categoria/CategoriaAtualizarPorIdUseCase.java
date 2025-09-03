package school.sptech.CleanArchitecture.core.application.usecase.categoria;

import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.application.command.categoria.CategoriaAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.command.categoria.CriarCategoriaCommand;
import school.sptech.CleanArchitecture.core.application.exception.categoria.CategoriaConflitoException;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;

public class CategoriaAtualizarPorIdUseCase {

    private final CategoriaGateway gateway;

    public CategoriaAtualizarPorIdUseCase(CategoriaGateway gateway) {
        this.gateway = gateway;
    }

    public Categoria execute(CategoriaAtualizarCommand command){
        if(gateway.existsById(command.id())){
            if (gateway.existsByNome(command.nome())){
                throw new CategoriaConflitoException("Categoria com nome "+ command.nome()+" já cadastrada.");
            }
            var categoriaPai = new Categoria();
            categoriaPai.setIdCategoria(command.categoriaPai().idCategoria());
            var categoriaParaAtualizar = new Categoria();
            categoriaParaAtualizar.setIdCategoria(command.id());
            categoriaParaAtualizar.setNome(command.nome());
            categoriaParaAtualizar.setCategoriaPai(categoriaPai);
            return gateway.save(categoriaParaAtualizar);
        }
        throw new CategoriaNaoEncontradaException("Categoria com id "+ command.id() +" não encontrada.");
    }
}
