package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.permissao;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.PermissaoGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Permissao;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissaoAdapter implements PermissaoGateway {

    private final PermissaoRepository permissaoRepository;

    public PermissaoAdapter(PermissaoRepository permissaoRepository) {
        this.permissaoRepository = permissaoRepository;
    }

    @Override
    public List<Permissao> findAll() {
        return permissaoRepository.findAll()
                .stream()
                .map(PermissaoEntityMapper::ofEntity)
                .collect(Collectors.toList());
    }
}
