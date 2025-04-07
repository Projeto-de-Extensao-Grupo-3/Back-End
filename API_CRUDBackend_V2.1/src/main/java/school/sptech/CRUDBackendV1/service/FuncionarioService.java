package school.sptech.CRUDBackendV1.service;

import lombok.Setter;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackendV1.entity.Funcionario;
import school.sptech.CRUDBackendV1.exception.funcionario.FuncionarioConflitoException;
import school.sptech.CRUDBackendV1.exception.funcionario.FuncionarioNaoEncontradoException;
import school.sptech.CRUDBackendV1.repository.FuncionarioRepository;

import java.util.List;

@Service
@Setter
public class FuncionarioService {
    private FuncionarioRepository repository;

    public Funcionario cadastrarFuncionario(Funcionario funcionarioCad) {
        if (repository.existsByCpfOrEmail(funcionarioCad.getCpf(), funcionarioCad.getEmail())) {
            throw new FuncionarioConflitoException("Este funcionário já existe no sistema.");
        }
        return repository.save(funcionarioCad);
    }

    public List<Funcionario> verificarTodosFuncionarios() {
        return repository.findAll();
    }

//    public boolean loginFuncionario(Funcionario funcionarioLogar) {
//        Funcionario buscarFuncionario = repository.findByEmailAndSenha(
//                funcionarioLogar.getEmail(),
//                funcionarioLogar.getSenha()
//        );
//
//        if (buscarFuncionario == null) {
//            return false;
//        }
//        return true;
//    }

    public Funcionario atualizarFuncionarioPorId (Integer id, Funcionario funcionarioAtualizar) {
        if(repository.existsById(id)) {
            funcionarioAtualizar.setIdFuncionario(id);
            return repository.save(funcionarioAtualizar);
        }
        throw new FuncionarioNaoEncontradoException("O funcionário para atualizar não existe.");
    }

    public void removerFuncionarioPorId(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        throw new FuncionarioNaoEncontradoException("O funcionário para atualizar não existe.");
    }
}
