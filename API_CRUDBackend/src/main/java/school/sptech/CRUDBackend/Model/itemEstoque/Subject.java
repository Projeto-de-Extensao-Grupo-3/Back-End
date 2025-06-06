package school.sptech.CRUDBackend.Model.itemEstoque;

import school.sptech.CRUDBackend.Model.loteItemEstoque.ObserverLoteItem;
import school.sptech.CRUDBackend.entity.ItemEstoque;

public interface Subject {
    void adicionarObservador(Observer observador);
    void removerObservador(Observer observador);
    void notificarObservers(ItemEstoque itemEstoque);
}
