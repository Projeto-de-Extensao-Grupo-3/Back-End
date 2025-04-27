package school.sptech.CRUDBackend.service.serviceLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.CRUDBackend.config.GerenciadorTokenJwt;
import school.sptech.CRUDBackend.dto.dtoLogin.FuncionarioListarDto;
import school.sptech.CRUDBackend.dto.dtoLogin.FuncionarioMapper;
import school.sptech.CRUDBackend.dto.dtoLogin.FuncionarioTokenDto;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CRUDBackend.repository.FuncionarioRepository;

import java.util.List;

@Service
public class FuncionarioServiceLogin {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void criar(Funcionario novoFuncionario) {

        String senhaCriptografada = passwordEncoder.encode(novoFuncionario.getSenha());
        novoFuncionario.setSenha(senhaCriptografada);

        this.funcionarioRepository.save(novoFuncionario);
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

        return FuncionarioMapper.of(funcionarioAutenticado, token);
    }

    public List<FuncionarioListarDto> listarTodos(){

        List<Funcionario> funcionariosEncontrados = funcionarioRepository.findAll();
        return funcionariosEncontrados.stream().map(FuncionarioMapper::of).toList();
    }
}
