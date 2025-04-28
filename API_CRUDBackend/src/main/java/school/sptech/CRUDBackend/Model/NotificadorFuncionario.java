package school.sptech.CRUDBackend.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.entity.ItemEstoque;
import school.sptech.CRUDBackend.repository.FuncionarioRepository;

import java.util.List;
@Component
public class NotificadorFuncionario implements FuncionarioObserver {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public void atualizar(ItemEstoque item) {
        System.out.println("Entrei no atualizar!");

        Funcionario func = new Funcionario();
        func.setNome("Leandro");
        func.setEmail("Leandro03@gmail.com");
        funcionarioRepository.save(func);

        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        for (Funcionario funcionario : funcionarios) {
            System.out.println("Email enviado para (" + funcionario.getEmail() +
                    ") sobre o item: " + item.getDescricao());
        }
    }
}
