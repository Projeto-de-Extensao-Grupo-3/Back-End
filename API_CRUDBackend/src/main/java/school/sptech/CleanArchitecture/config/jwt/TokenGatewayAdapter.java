package school.sptech.CleanArchitecture.config.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import school.sptech.CleanArchitecture.core.adapters.TokenGateway;

@Service
public class TokenGatewayAdapter implements TokenGateway {

    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    public TokenGatewayAdapter(GerenciadorTokenJwt gerenciadorTokenJwt) {
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
    }

    @Override
    public String generateToken(Authentication authentication) {
        return gerenciadorTokenJwt.generateToken(authentication);
    }
}