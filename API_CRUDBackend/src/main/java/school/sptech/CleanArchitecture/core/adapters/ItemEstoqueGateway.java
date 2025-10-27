package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

import java.util.List;
import java.util.Optional;

public interface ItemEstoqueGateway {

    ItemEstoque save(ItemEstoque itemEstoque);

    List<ItemEstoque> findAll();

    boolean existsById(Integer id);

    Optional<ItemEstoque> findById(Integer id);

    void deleteById(Integer id);

    Boolean existsByDescricao(String descricao);

    List<ItemEstoque> findByTipo(String tipo);

    List<ItemEstoque> findByDescricaoContainsIgnoreCase(String descricao, String tipo);

    Double calcularCustoProducao(Integer id);
}
