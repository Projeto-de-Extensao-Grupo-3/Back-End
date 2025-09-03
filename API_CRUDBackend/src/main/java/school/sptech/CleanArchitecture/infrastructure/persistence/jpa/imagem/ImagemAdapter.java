package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem;

import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.ImagemGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Imagem;

@Service
public class ImagemAdapter implements ImagemGateway {

    private final ImagemRepository repository;

    public ImagemAdapter(ImagemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean existsByUrl(String url) {
        return repository.existsByUrl(url);
    }

    @Override
    public Imagem save(Imagem imagem) {
        return null;
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
