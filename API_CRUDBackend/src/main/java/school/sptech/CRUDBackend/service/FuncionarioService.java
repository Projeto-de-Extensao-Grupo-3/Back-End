package school.sptech.CRUDBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.CRUDBackend.config.GerenciadorTokenJwt;
import school.sptech.CRUDBackend.dto.dtoLogin.FuncionarioLoginMapper;
import school.sptech.CRUDBackend.dto.dtoLogin.FuncionarioTokenDto;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.exception.funcionario.FuncionarioConflitoException;
import school.sptech.CRUDBackend.exception.funcionario.FuncionarioNaoEncontradoException;
import school.sptech.CRUDBackend.repository.FuncionarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public Funcionario cadastrarFuncionario(Funcionario novoFuncionario) {
        if (funcionarioRepository.existsByCpfOrEmail(novoFuncionario.getCpf(), novoFuncionario.getEmail())) {
            throw new FuncionarioConflitoException("Este funcionário já existe no sistema.");
        }
        String senhaCriptografada = passwordEncoder.encode(novoFuncionario.getSenha());
        novoFuncionario.setSenha(senhaCriptografada);
        return funcionarioRepository.save(novoFuncionario);
    }

    public FuncionarioTokenDto autenticar(Funcionario funcionario) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                funcionario.getEmail(), funcionario.getSenha()
        );

        final Authentication authentication = authenticationManager.authenticate(credentials);

        Funcionario funcionarioAutenticado = funcionarioRepository.findByEmail(
                funcionario.getEmail()).orElseThrow(
                () -> new ResponseStatusException(404, "Email do usuário não cadastro", null)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return FuncionarioLoginMapper.of(funcionarioAutenticado, token);
    }

    public List<Funcionario> verificarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("O funcionário não existe."));
    }

    public Funcionario atualizarFuncionarioPorId(Integer id, Funcionario funcionarioAtualizar) {
        if (funcionarioRepository.existsById(id)) {
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
