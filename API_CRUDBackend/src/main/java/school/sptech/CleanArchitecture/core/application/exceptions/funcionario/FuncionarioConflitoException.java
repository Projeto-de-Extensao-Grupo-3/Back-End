package school.sptech.CleanArchitecture.core.application.exceptions.funcionario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FuncionarioConflitoException extends RuntimeException {
    public FuncionarioConflitoException(String message) {
        super(message);
    }
}
