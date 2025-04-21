package school.sptech.CRUDBackend.Model;
import school.sptech.CRUDBackend.entity.ItemEstoque;

public interface Subject {

    void adicionarObservador(Observer observador);
    void removerObservador(Observer observador);
    void notificarObservers(ItemEstoque item);
}
