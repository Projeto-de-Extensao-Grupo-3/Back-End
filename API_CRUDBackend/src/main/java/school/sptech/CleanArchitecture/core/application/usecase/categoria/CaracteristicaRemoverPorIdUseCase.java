package school.sptech.CleanArchitecture.core.application.usecase.categoria;

import jakarta.transaction.Transactional;
import school.sptech.CRUDBackend.exception.Categoria.CategoriaNaoEncontradaException;
import school.sptech.CleanArchitecture.core.adapters.CategoriaGateway;
import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.categoria.CategoriaBadRequestException;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;

public class CaracteristicaRemoverPorIdUseCase {

    private final CategoriaGateway gateway;

    private final ItemEstoqueGateway itemEstoqueGateway;

    public CaracteristicaRemoverPorIdUseCase(CategoriaGateway gateway, ItemEstoqueGateway itemEstoqueGateway) {
        this.gateway = gateway;
        this.itemEstoqueGateway = itemEstoqueGateway;
    }

    @Transactional
    public void execute(Integer id){
        if (gateway.existsById(id)) {
            Categoria caracteristica = gateway.findById(id);
            Integer idPai = caracteristica.getCategoriaPai().getIdCategoria();
            if (idPai == 3){
                itemEstoqueGateway.removerCaracteristica(id);
                gateway.delete(caracteristica);
                return;
            }
            throw new CategoriaBadRequestException("O objeto não corresponde a uma caracteristica pois não possui ID da categoria pai como 3.");
        } else {
            throw new CategoriaNaoEncontradaException("Categoria com ID " + id + " não encontrada");
        }
    }

}
