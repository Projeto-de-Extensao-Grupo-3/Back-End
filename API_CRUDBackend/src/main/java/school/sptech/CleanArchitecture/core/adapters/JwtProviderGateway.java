
package school.sptech.CleanArchitecture.core.adapters;

import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

public interface JwtProviderGateway {
    String generateToken(Funcionario funcionario);
    String getUsernameFromToken(String token);
    boolean validateToken(String token, String expectedUsername);
}
