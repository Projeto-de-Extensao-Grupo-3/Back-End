package school.sptech.CleanArchitecture.infrastructure.persistence.jpa.alerta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.itemEstoque.ItemEstoqueEntity;


import java.util.List;

public interface AlertaRepository extends JpaRepository<AlertaEntity, Integer> {
//    List<AlertaEntity> findByItemEstoque(ItemEstoqueEntity itemEstoque);
}
