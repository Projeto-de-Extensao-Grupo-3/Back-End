package school.sptech.CRUDBackendV1.exception.funcionario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException(String message) {
        super(message);
    }
}
