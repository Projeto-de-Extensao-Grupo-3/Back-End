package school.sptech.CRUDBackend.Model.loteItemEstoque;

import school.sptech.CRUDBackend.Model.itemEstoque.Observer;
import school.sptech.CRUDBackend.entity.ItemEstoque;

public interface SubjectLoteItem {
    void adicionarObservadorLoteItem(ObserverLoteItem observadorLoteItem);
    void removerObservadorLoteItem(ObserverLoteItem observadorLoteIem);
    void notificarObserversLoteItem(ItemEstoque itemEstoque);
}
