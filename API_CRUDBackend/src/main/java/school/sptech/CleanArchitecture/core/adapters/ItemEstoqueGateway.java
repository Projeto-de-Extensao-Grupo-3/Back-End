package school.sptech.CleanArchitecture.core.adapters;

import org.springframework.data.repository.query.Param;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.DefeitosPorRoupaDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.EvolucaoVendasDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.ProdutoBaixoGiroDto;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface ItemEstoqueGateway {

    ItemEstoque save(ItemEstoque itemEstoque);

    List<ItemEstoque> findAll();

    List<ProdutoBaixoGiroDto> produtoGiroBaixo(String caracteristica, String categoria);

    List<DefeitosPorRoupaDto> defeitosPorRoupa(String dataInicio, String dataFim, String caracteristica, String categoria);

    List<EvolucaoVendasDto> evolucaoVendas(String dataInicio, String dataFim, String caracteristica, String categoria);

    boolean existsById(Integer id);

    Optional<ItemEstoque> findById(Integer id);

    void deleteById(Integer id);

    Boolean existsByDescricao(String descricao);

    List<ItemEstoque> findByTipo(String tipo);

    List<ItemEstoque> findByDescricaoContainsIgnoreCase(String descricao, String tipo);

    Double calcularCustoProducao(Integer id);

    List<ItemEstoque> findByCategoria(Categoria categoria);

    List<ItemEstoque> findByCaracteristicas_IdCategoria(Integer idCategoria);

    void removerCaracteristica(Integer idCategoria);
}
