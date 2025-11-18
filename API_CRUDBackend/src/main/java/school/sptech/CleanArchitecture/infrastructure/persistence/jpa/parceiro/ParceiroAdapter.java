package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.ParceiroGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

import java.util.List;
import java.util.Optional;

@Service
public class ParceiroAdapter implements ParceiroGateway {

    private final ParceiroRepository parceiroRepository;

    public ParceiroAdapter(ParceiroRepository parceiroRepository) {
        this.parceiroRepository = parceiroRepository;
    }

    @Override
    public Parceiro save(Parceiro parceiro) {
        ParceiroEntity parceiroEntity = ParceiroEntityMapper.ofDomain(parceiro);
        ParceiroEntity parceiroSalvarNoBanco = parceiroRepository.save(parceiroEntity);
        return ParceiroEntityMapper.ofEntity(parceiroSalvarNoBanco);
    }

    @Override
    public boolean existsById(Integer id) {
        return parceiroRepository.existsById(id);
    }

    @Override
    public Optional<Parceiro> findById(Integer id) {
        return parceiroRepository.findById(id).map(ParceiroEntityMapper::ofEntity);
    }

    @Override
    public List<Parceiro> findAll() {
        return parceiroRepository.findAll()
                .stream()
                .map(ParceiroEntityMapper::ofEntity)
                .toList();
    }

    @Override
    public Parceiro findByNome(String nome) {
        return ParceiroEntityMapper.ofEntity(parceiroRepository.findByNome(nome));
    }

    @Override
    public void deleteById(Integer id) {
        parceiroRepository.deleteById(id);
    }

    @Override
    public Boolean existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(String email, String identificacao, String endereco) {
        return parceiroRepository.existsByEmailOrIdentificacaoOrEnderecoAllIgnoreCase(email, identificacao, endereco);
    }

    @Override
    public List<Parceiro> findByCategoriaOrderByIdParceiroDesc(String categoria) {
        return parceiroRepository.findByCategoriaAndIdentificacaoIsNotNullOrderByIdParceiroDesc(categoria)
                .stream()
                .map(ParceiroEntityMapper::ofEntity)
                .toList();
    }

    @Override
    public List<Parceiro> findByCategoriaAndNomeContainsIgnoreCase(String categoria, String nome) {
        return parceiroRepository.findByCategoriaAndNomeContainsIgnoreCaseAndIdentificacaoIsNotNull(categoria, nome)
                .stream()
                .map(ParceiroEntityMapper::ofEntity)
                .toList();
    }
}
