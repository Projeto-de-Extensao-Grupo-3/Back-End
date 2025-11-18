package school.sptech.CleanArchitecture.core.application.usecase.categoria;

import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.application.command.categoria.CategoriaAtualizarCommand;
import school.sptech.CleanArchitecture.core.application.exceptions.categoria.CategoriaConflitoException;
import school.sptech.CleanArchitecture.core.application.mapper.CategoriaMapper;
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

            var categoriaParaAtualizar = CategoriaMapper.ofAtualizarCategoriaCommand(command);

            return gateway.save(categoriaParaAtualizar);
        }
        throw new CategoriaNaoEncontradaException("Categoria com id "+ command.id() +" não encontrada.");
    }
}
