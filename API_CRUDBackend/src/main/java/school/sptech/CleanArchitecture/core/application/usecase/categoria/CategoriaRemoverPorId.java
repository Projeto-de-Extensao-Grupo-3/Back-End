package school.sptech.CleanArchitecture.core.application.usecase.categoria;

import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.categoria.CategoriaEmItemException;
import school.sptech.CleanArchitecture.core.application.exceptions.categoria.CategoriaPaiException;
import school.sptech.CleanArchitecture.core.application.usecase.itemEstoque.ItemEstoqueListarItensCategoriaUseCase;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriaRemoverPorId {

    private final CategoriaGateway gateway;

    private final ItemEstoqueListarItensCategoriaUseCase listarItensCategoriaUseCase;

    public CategoriaRemoverPorId(CategoriaGateway gateway, ItemEstoqueListarItensCategoriaUseCase listarItensCategoriaUseCase) {
        this.gateway = gateway;
        this.listarItensCategoriaUseCase = listarItensCategoriaUseCase;
    }

    public void execute(Integer id){
        if (gateway.existsById(id)) {
            if (id < 4){
                throw new CategoriaPaiException("Não é possível remover uma categoria pai! IDs: 1, 2 ou 3");
            }
            Categoria categoria = gateway.findById(id);
            Integer idPai = categoria.getCategoriaPai().getIdCategoria();
            if (idPai == 1 || idPai == 2){
                List<ItemEstoque> itensComCategoria = listarItensCategoriaUseCase.execute(id);
                if (!itensComCategoria.isEmpty()) {
                    String itens = itensComCategoria.stream()
                            .map(item -> "ID: " + item.getIdItemEstoque() + " - Nome: " + item.getDescricao())
                            .collect(Collectors.joining(", "));

                    throw new CategoriaEmItemException(
                            "Os seguintes itens ainda estão com a categoria atribuída: " + itens
                    );
                }
                gateway.deleteById(id);
            }
        } else {
            throw new CategoriaNaoEncontradaException("Categoria com ID " + id + " não encontrada");
        }
    }

}
