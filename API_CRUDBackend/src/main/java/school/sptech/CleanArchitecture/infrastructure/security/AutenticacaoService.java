
package school.sptech.CleanArchitecture.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioAdapter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final FuncionarioAdapter funcionarioAdapter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var funcionario = funcionarioAdapter.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("funcionario nao encontrado: " + username));

        List<GrantedAuthority> authorities = funcionario.getPermissoes() == null ? List.of() :
                funcionario.getPermissoes().stream()
                        .map(p -> new SimpleGrantedAuthority(p.getDescricao()))
                        .toList();

        return new User(funcionario.getEmail(), funcionario.getSenha(), authorities);
    }
}
