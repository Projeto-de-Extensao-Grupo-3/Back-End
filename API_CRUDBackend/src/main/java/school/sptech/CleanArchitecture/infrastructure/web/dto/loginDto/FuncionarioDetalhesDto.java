package school.sptech.CleanArchitecture.infrastructure.web.dto.loginDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.CRUDBackend.entity.Funcionario;
import school.sptech.CleanArchitecture.core.domain.entity.Permissao;
import school.sptech.CleanArchitecture.infrastructure.persistence.jpa.funcionario.FuncionarioEntity;
import school.sptech.CleanArchitecture.infrastructure.web.dto.permissao.PermissaoDTOMapper;
import school.sptech.CleanArchitecture.infrastructure.web.dto.permissao.PermissaoListDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class FuncionarioDetalhesDto implements UserDetails {

    private final Integer idFuncionario;
    private final String nome;
    private final String email;
    private final String senha;
    private final List<PermissaoListDTO> permissoes;

    // Construtor adicional
    public FuncionarioDetalhesDto(FuncionarioEntity funcionario) {
        this.idFuncionario = funcionario.getIdFuncionario();
        this.nome = funcionario.getNome();
        this.email = funcionario.getEmail();
        this.senha = funcionario.getSenha();
        this.permissoes = funcionario.getPermissoes()
                .stream()
                .map(PermissaoDTOMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
