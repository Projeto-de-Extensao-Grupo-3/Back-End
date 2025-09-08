package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.LoteItemEstoque;
import school.sptech.CleanArchitecture.core.domain.observer.Observer;

import java.util.List;

public interface LoteItemEstoqueGateway {
    LoteItemEstoque save(LoteItemEstoque lote);

    LoteItemEstoque findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    List<LoteItemEstoque> findAll();

    // Métodos para Observer
    void adicionarObservador(Observer observador);
    void removerObservador(Observer observador);
    void notificarObservers(LoteItemEstoque loteItemEstoque);
}
