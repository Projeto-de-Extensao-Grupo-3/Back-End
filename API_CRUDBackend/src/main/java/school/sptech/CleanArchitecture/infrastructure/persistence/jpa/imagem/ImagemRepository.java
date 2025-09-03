package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.imagem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<ImagemEntity, Integer> {

    Boolean existsByUrl(String url);
}
