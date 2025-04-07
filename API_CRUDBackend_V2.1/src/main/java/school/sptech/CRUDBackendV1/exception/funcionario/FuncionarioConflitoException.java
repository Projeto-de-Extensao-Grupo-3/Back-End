package school.sptech.CRUDBackendV1.exception.funcionario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FuncionarioConflitoException extends RuntimeException {
    public FuncionarioConflitoException(String message) {
        super(message);
    }
}
