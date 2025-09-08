package school.sptech.CleanArchitecture.core.domain.observer;


import school.sptech.CleanArchitecture.core.domain.entity.ItemEstoque;

public interface SubjectLoteItem {
    void adicionarObservadorLoteItem(ObserverLoteItem observadorLoteItem);
    void removerObservadorLoteItem(ObserverLoteItem observadorLoteIem);
    void notificarObserversLoteItem(ItemEstoque itemEstoque);
}
