package school.sptech.CRUDBackend.repository;

import org.hibernate.sql.exec.internal.JdbcParameterBindingImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.CRUDBackend.entity.LoteItemEstoque;

public interface LoteItemEstoqueRepository extends JpaRepository<LoteItemEstoque, Integer> {

}
