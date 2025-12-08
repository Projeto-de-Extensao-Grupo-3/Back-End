package school.sptech.CleanArchitecture.core.domain.valueObject;

import java.util.Objects;
import java.util.regex.Pattern;

public final class CpfVo {

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{11}");
    private final String value;

    public CpfVo(String value) {
        String digitsOnly = null;
        if (value != null) {
            digitsOnly = value.replaceAll("[.-]", ""); // remove pontos e traços
            if (!CPF_PATTERN.matcher(digitsOnly).matches()) {
                throw new IllegalArgumentException("CPF deve conter 11 dígitos numéricos");
            }

//            if (!(digitsOnly.chars().distinct().count() == 1)) {
//                throw new IllegalArgumentException("CPF inválido");
//            }
        }
        this.value = digitsOnly;
    }

    public String getValue() {
        return value;
    }

    // Validação de CPF (regra oficial)
//    private boolean isValidCpf(String cpf) {
//        if (cpf.chars().distinct().count() == 1) return false; // evita CPFs com dígitos repetidos (ex: 11111111111)
//
//        int digito1 = calcularDigito(cpf.substring(0, 9), 10);
//        int digito2 = calcularDigito(cpf.substring(0, 9) + digito1, 11);
//
//        return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
//    }

//    private int calcularDigito(String base, int peso) {
//        int soma = 0;
//        for (char c : base.toCharArray()) {
//            soma += (c - '0') * peso--;
//        }
//        int resto = soma % 11;
//        return (resto < 2) ? 0 : 11 - resto;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CpfVo)) return false;
        CpfVo cpf = (CpfVo) o;
        return Objects.equals(value, cpf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.substring(0, 3) + "." +
                value.substring(3, 6) + "." +
                value.substring(6, 9) + "-" +
                value.substring(9, 11);
    }

}
