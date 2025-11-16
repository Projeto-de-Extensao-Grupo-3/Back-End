package school.sptech.CleanArchitecture.core.domain.valueObject;

import java.util.Objects;
import java.util.regex.Pattern;

public final class EmailVo {

    // Regex básica para email válido
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    private final String value;

    public EmailVo(String value) {

        if (value != null && !EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Email inválido");
        }

        this.value = value != null ? value.toLowerCase() : value; // normaliza
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailVo)) return false;
        EmailVo email = (EmailVo) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
