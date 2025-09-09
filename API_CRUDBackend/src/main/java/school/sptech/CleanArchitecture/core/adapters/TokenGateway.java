package school.sptech.CleanArchitecture.core.adapters;

import org.springframework.security.core.Authentication;

public interface TokenGateway {
    String generateToken(Authentication authentication);
}
