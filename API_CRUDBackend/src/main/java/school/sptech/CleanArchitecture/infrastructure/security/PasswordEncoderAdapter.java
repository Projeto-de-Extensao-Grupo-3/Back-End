
package school.sptech.CleanArchitecture.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import school.sptech.CleanArchitecture.core.adapters.PasswordEncoderGateway;

@Component
@RequiredArgsConstructor
public class PasswordEncoderAdapter implements PasswordEncoderGateway {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String raw) {
        return passwordEncoder.encode(raw);
    }

    @Override
    public boolean matches(String raw, String encoded) {
        return passwordEncoder.matches(raw, encoded);
    }
}
