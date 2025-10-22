package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.loteItemEstoque;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.LoteItemEstoqueGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.LoteItemEstoque.LoteItemEstoqueNaoEncontradoException;
import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.EntradaPaginacaoDTO;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.PaginacaoResponseDTO;
import school.sptech.CleanArchitecture.infrastructure.web.dto.loteItemEstoque.SaidaPaginacaoDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoteItemEstoqueAdapter implements LoteItemEstoqueGateway {

    private final LoteItemEstoqueRepository repository;

    private final List<Observer> observers = new ArrayList<>();

    public LoteItemEstoqueAdapter(LoteItemEstoqueRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoteItemEstoque save(LoteItemEstoque lote) {
        LoteItemEstoqueEntity entity = LoteItemEstoqueEntityMapper.ofDomain(lote);
        LoteItemEstoqueEntity savedEntity = repository.save(entity);
        return LoteItemEstoqueEntityMapper.ofEntity(savedEntity);
    }

    @Override
    public LoteItemEstoque findById(Integer id) {
        LoteItemEstoqueEntity entity = repository.findById(id)
                .orElseThrow(() -> new LoteItemEstoqueNaoEncontradoException("LoteItemEstoque com ID " + id + " não encontrado"));
        return LoteItemEstoqueEntityMapper.ofEntity(entity);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<LoteItemEstoque> findAll() {
        return repository.findAll()
                .stream()
                .map(LoteItemEstoqueEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public LoteItemEstoque buscarPorId(Integer id) {
        LoteItemEstoqueEntity loteItemEstoqueEntity = repository.findById(id).orElse(null);
        return LoteItemEstoqueEntityMapper.ofEntity(loteItemEstoqueEntity);
    }

    @Override
    public PaginacaoResponseDTO<EntradaPaginacaoDTO> buscarPaginado(int offset, int limit) {
        List<Object[]> resultado = repository.buscarPaginado(offset, limit);

        List<EntradaPaginacaoDTO> conteudo = resultado.stream()
                .map(obj -> new EntradaPaginacaoDTO(
                        (String) obj[0],
                        (String) obj[1],
                        obj[2] != null ? ((Number) obj[2]).doubleValue() : null,
                        (Integer) obj[3],
                        (String) obj[4]
                        ,(Timestamp) obj[5]
                ))
                .collect(Collectors.toList());

        Long total = repository.contarTotal();
        int paginaAtual = offset / limit;

        return new PaginacaoResponseDTO<>(conteudo, total, paginaAtual, limit);
    }

    @Override
    public PaginacaoResponseDTO<SaidaPaginacaoDTO> buscarPaginadoSaida(int offset, int limit) {
        List<Object[]> resultados = repository.buscarSaidaPaginada(offset, limit);

        List<SaidaPaginacaoDTO> conteudo = resultados.stream()
                .map(obj -> new SaidaPaginacaoDTO(
                        (String) obj[0],
                        (String) obj[1],
                        ((Number) obj[2]).doubleValue(),
                        (Integer) obj[3],
                        (String) obj[4],
                        (Timestamp) obj[5]
                ))
                .collect(Collectors.toList());

        Long total = repository.contarTotal();
        int paginaAtual = offset / limit;
        return new PaginacaoResponseDTO<>(conteudo, total, paginaAtual, limit);
    }
}
