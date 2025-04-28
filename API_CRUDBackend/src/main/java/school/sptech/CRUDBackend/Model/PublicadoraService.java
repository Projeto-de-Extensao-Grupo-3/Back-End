package school.sptech.CRUDBackend.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.ItemEstoque;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicadoraService implements ItemSubject {

    private List<FuncionarioObserver> observadores = new ArrayList<>();

    @Autowired
    public PublicadoraService(NotificadorFuncionario notificadorFuncionario) {
        adicionarObservador(notificadorFuncionario);
    }

    @Override
    public void adicionarObservador(FuncionarioObserver observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(FuncionarioObserver observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservers(ItemEstoque item){

        for (FuncionarioObserver obs : observadores) {
            obs.atualizar(item);
        }
    }

    public void verificarEstoque(ItemEstoque item) {
        if (item.getQtdArmazenado() <= item.getQtdMinimo()){
            notificarObservers(item);
        }
    }
}
