package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.Imagem;

public interface ImagemGateway {

    Boolean existsByUrl(String url);

    Imagem save(Imagem imagem);

    boolean existsById(Integer id);

    void deleteById(Integer id);
}
