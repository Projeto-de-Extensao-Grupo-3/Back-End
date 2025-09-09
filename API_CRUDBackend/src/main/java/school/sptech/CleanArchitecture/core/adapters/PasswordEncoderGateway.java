
package school.sptech.CleanArchitecture.core.adapters;

public interface PasswordEncoderGateway {
    String encode(String raw);
    boolean matches(String raw, String encoded);
}
