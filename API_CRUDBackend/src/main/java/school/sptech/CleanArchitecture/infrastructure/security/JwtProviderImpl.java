
package school.sptech.CleanArchitecture.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import school.sptech.CleanArchitecture.core.adapters.JwtProviderGateway;
import school.sptech.CleanArchitecture.core.domain.entity.Funcionario;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProviderImpl implements JwtProviderGateway {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.validity}")
    private long jwtTokenValidity;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(Funcionario funcionario) {
        String authorities = funcionario.getPermissoes() == null ? "" :
                funcionario.getPermissoes().stream()
                        .map(p -> p.getDescricao())
                        .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(funcionario.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity * 1000))
                .signWith(key())
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public boolean validateToken(String token, String expectedUsername) {
        var claims = getClaims(token);
        var expired = claims.getExpiration().before(new Date(System.currentTimeMillis()));
        return !expired && expectedUsername.equals(claims.getSubject());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
