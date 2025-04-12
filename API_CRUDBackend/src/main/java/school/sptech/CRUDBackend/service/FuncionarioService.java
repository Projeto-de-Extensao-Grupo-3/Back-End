package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.exception.funcionario.FuncionarioConflitoException;
import school.sptech.CRUDBackend.exception.funcionario.FuncionarioNaoEncontradoException;
import school.sptech.CRUDBackend.repository.FuncionarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public Funcionario cadastrarFuncionario(Funcionario funcionarioCad) {
        if (funcionarioRepository.existsByCpfOrEmail(funcionarioCad.getCpf(), funcionarioCad.getEmail())) {
            throw new FuncionarioConflitoException("Este funcionário já existe no sistema.");
        }
        return funcionarioRepository.save(funcionarioCad);
    }

    public List<Funcionario> verificarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("O funcionário para atualizar não existe."));
    }

    public Funcionario atualizarFuncionarioPorId (Integer id, Funcionario funcionarioAtualizar) {
        if(funcionarioRepository.existsById(id)) {
            funcionarioAtualizar.setIdFuncionario(id);
            return funcionarioRepository.save(funcionarioAtualizar);
        }
        throw new FuncionarioNaoEncontradoException("O funcionário para atualizar não existe.");
    }

    public void removerFuncionarioPorId(Integer id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new FuncionarioNaoEncontradoException("O funcionário para atualizar não existe.");
        }
    }
}
