package school.sptech.CRUDBackend.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.ItemEstoque;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicadoraService implements Subject{

    private List<Observer> observadores = new ArrayList<>();

    @Autowired
    public PublicadoraService(NotificadorFuncionario notificadorFuncionario) {
        adicionarObservador(notificadorFuncionario);
    }

    @Override
    public void adicionarObservador(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(Observer observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservers(ItemEstoque item){

        for (Observer obs : observadores) {
            obs.atualizar(item);
        }
    }

    public void verificarEstoque(ItemEstoque item){
        if (item.getQtdArmazenado() <= item.getQtdMinima()){
            notificarObservers(item);
        }
    }
}
