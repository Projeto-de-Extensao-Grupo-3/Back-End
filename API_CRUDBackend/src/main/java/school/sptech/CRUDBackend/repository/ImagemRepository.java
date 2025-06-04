package school.sptech.CRUDBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.CRUDBackend.entity.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    Boolean existsByUrl(String url);
}
