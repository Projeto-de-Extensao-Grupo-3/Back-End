package school.sptech.CRUDBackend.Model;
import school.sptech.CRUDBackend.entity.ItemEstoque;

public interface ItemSubject {

    void adicionarObservador(FuncionarioObserver observador);
    void removerObservador(FuncionarioObserver observador);
    void notificarObservers(ItemEstoque item);
}
