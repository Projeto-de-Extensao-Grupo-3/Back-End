package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.prateleira;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.PrateleiraGateway;
import school.sptech.CleanArchitecture.core.application.exceptions.Prateleira.PrateleiraNaoEncontradaException;
import school.sptech.CleanArchitecture.core.domain.entity.Prateleira;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrateleiraAdapter implements PrateleiraGateway {

    private final PrateleiraRepository prateleiraRepository;

    public PrateleiraAdapter(PrateleiraRepository prateleiraRepository) {
        this.prateleiraRepository = prateleiraRepository;
    }

    @Override
    public Prateleira save(Prateleira prateleira) {
        PrateleiraEntity prateleiraEntity = PrateleiraEntityMapper.ofDomain(prateleira);
        PrateleiraEntity prateleiraSalvaNoBanco = prateleiraRepository.save(prateleiraEntity);
        return PrateleiraEntityMapper.ofEntity(prateleiraSalvaNoBanco);
    }

    @Override
    public List<Prateleira> findAll() {
        return prateleiraRepository.findAll()
                .stream()
                .map(PrateleiraEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Prateleira findById(Integer id) {
        PrateleiraEntity prateleira = prateleiraRepository.findById(id)
                .orElseThrow(() -> new PrateleiraNaoEncontradaException("Prateleira com ID " + id + " não encontrada"));
        return PrateleiraEntityMapper.ofEntity(prateleira);
    }

    @Override
    public boolean existsByCodigo(String codigo) {
        return prateleiraRepository.existsByCodigo(codigo);
    }

    @Override
    public Prateleira findByCodigo(String codigo) {
        return PrateleiraEntityMapper.ofEntity(prateleiraRepository.findByCodigo(codigo)
                .orElseThrow(() -> new school.sptech.CRUDBackend.exception.Prateleira.PrateleiraNaoEncontradaException("Prateleira com Codigo " + codigo + " não encontrada")));
    }

    @Override
    public boolean existsById(Integer id) {
        return prateleiraRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        prateleiraRepository.deleteById(id);
    }
}
