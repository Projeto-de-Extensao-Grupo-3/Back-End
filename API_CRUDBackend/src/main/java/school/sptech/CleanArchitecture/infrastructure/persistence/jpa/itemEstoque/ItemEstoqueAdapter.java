package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.ItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Categoria;
import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntity;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaEntityMapper;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.categoria.CategoriaRepository;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira.PrateleiraEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.DefeitosPorRoupaDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.EvolucaoVendasDto;
import school.sptech.CleanArchitecture.infrastructure.web.dto.itemEstoque.ProdutoBaixoGiroDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemEstoqueAdapter implements ItemEstoqueGateway {

    private final ItemEstoqueRepository repository;

    @Override
    public ItemEstoque save(ItemEstoque itemEstoque) {
        ItemEstoqueEntity entity = ItemEstoqueEntityMapper.ofDomain(itemEstoque);
        ItemEstoque retorno =  ItemEstoqueEntityMapper.ofEntity(repository.save(entity));
        return retorno;
    }

    @Override
    public List<ItemEstoque> findAll() {
        return repository.findAll().stream()
                .map(ItemEstoqueEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<ItemEstoque> findById(Integer id) {
        return repository.findById(id)
                .map(ItemEstoqueEntityMapper::ofEntity);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Boolean existsByDescricao(String descricao) {
        return repository.existsByDescricao(descricao);
    }

    @Override
    public List<ItemEstoque> findByTipo(String tipo) {
        return repository.findByCategoria_CategoriaPai_NomeOrderByIdItemEstoqueDesc(tipo).stream()
                .map(ItemEstoqueEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemEstoque> findByDescricaoContainsIgnoreCase(String descricao, String tipo) {
        return repository.findByDescricaoContainsIgnoreCaseAndCategoria_CategoriaPai_Nome(descricao, tipo).stream()
                .map(ItemEstoqueEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Double calcularCustoProducao(Integer id) {
        return repository.calcularCustoProducao(id);
    }

    @Override
    public List<ItemEstoque> findByCategoria(Categoria categoria) {
        CategoriaEntity categoriaEntity = CategoriaEntityMapper.ofDomain(categoria);
        return repository.findByCategoria(categoriaEntity).stream()
                .map(ItemEstoqueEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemEstoque> findByCaracteristicas_IdCategoria(Integer idCategoria) {
       List<ItemEstoqueEntity> itensEstoque = repository.findByCaracteristicas_IdCategoria(idCategoria);
       return itensEstoque.stream().map(ItemEstoqueEntityMapper::ofEntity).collect(Collectors.toList());
    }

    @Override
    public void removerCaracteristica(Integer idCategoria) {
        repository.removerCaracteristica(idCategoria);
    }

    @Override
    public List<ProdutoBaixoGiroDto> produtoGiroBaixo() {
        return repository.buscarProdutosGiroBaixo();
    }

    @Override
    public List<DefeitosPorRoupaDto> defeitosPorRoupa() {
        return repository.buscarDefeitosPorProduto();
    }

    @Override
    public List<EvolucaoVendasDto> evolucaoVendas() {
        return repository.buscarEvolucaoVendas();
    }
}
