package school.sptech.CleanArchitecture.core.domain.observer;

import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public interface Subject {
    void adicionarObservador(Observer observador);
    void removerObservador(Observer observador);
    void notificarObservers(ItemEstoque itemEstoque);
}
