package school.sptech.CleanArchitecture.core.application.exception.funcionario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class FuncionarioConflitoException extends RuntimeException {
    public FuncionarioConflitoException(String message) {
        super(message);
    }
}
