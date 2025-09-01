//package school.sptech.CRUDBackend.service.serviceLogin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import school.sptech.CRUDBackend.dto.dtoLogin.FuncionarioDetalhesDto;
//import school.sptech.CRUDBackend.entity.Funcionario;
//import school.sptech.CRUDBackend.repository.FuncionarioRepository;
//
//import java.util.Optional;
//
//@Service
//public class AutenticacaoService implements UserDetailsService {
//
//    @Autowired
//    private FuncionarioRepository funcionarioRepository;
//
//    // Métoodo da interface implementada
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByEmail(username);
//
//        if (funcionarioOpt.isEmpty()) {
//            throw new UsernameNotFoundException(String.format("funcionario: %s nao encontrado", username));
//        }
//
//        return new FuncionarioDetalhesDto(funcionarioOpt.get());
//    }
//}
