package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.parceiro;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.ParceiroGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Parceiro;

import java.util.List;

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
    public Parceiro findById(Integer id) {
        ParceiroEntity parceiro = parceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parceiro com ID " + id + " não encontrado"));
        return ParceiroEntityMapper.ofEntity(parceiro);
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
    public List<Parceiro> findByCategoria(String categoria) {
        return parceiroRepository.findByCategoria(categoria)
                .stream()
                .map(ParceiroEntityMapper::ofEntity)
                .toList();
    }

    @Override
    public List<Parceiro> findByCategoriaAndNomeContainsIgnoreCase(String categoria, String nome) {
        return parceiroRepository.findByCategoriaAndNomeContainsIgnoreCase(categoria, nome)
                .stream()
                .map(ParceiroEntityMapper::ofEntity)
                .toList();
    }
}
