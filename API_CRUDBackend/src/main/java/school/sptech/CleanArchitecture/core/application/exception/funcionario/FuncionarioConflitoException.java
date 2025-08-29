package school.sptech.CleanArchitecture.core.application.exception.funcionario;

public class FuncionarioConflitoException extends RuntimeException {
    public FuncionarioConflitoException(String message) {
        super(message);
    }
}
