package school.sptech.CleanArchitecture.core.domain.valueObject;

import java.util.Objects;
import java.util.regex.Pattern;

public final class TelefoneVo {

    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10,11}");
    private final String value;

    public TelefoneVo(String value) {
        String digitsOnly = null;

        if (value != null) {
            digitsOnly = value.replaceAll("\\D", ""); // remove caracteres não numéricos

            if (!PHONE_PATTERN.matcher(digitsOnly).matches()) {
                throw new IllegalArgumentException("Telefone deve ter 10 ou 11 dígitos numéricos");
            }
        }
        this.value = digitsOnly;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelefoneVo)) return false;
        TelefoneVo telefone = (TelefoneVo) o;
        return Objects.equals(value, telefone.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        if (value.length() == 10) {
            return String.format("(%s) %s-%s",
                    value.substring(0, 2),
                    value.substring(2, 6),
                    value.substring(6));
        } else {
            return String.format("(%s) %s-%s",
                    value.substring(0, 2),
                    value.substring(2, 7),
                    value.substring(7));
        }
    }

}
